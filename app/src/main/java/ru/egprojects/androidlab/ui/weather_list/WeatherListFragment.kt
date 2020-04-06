package ru.egprojects.androidlab.ui.weather_list

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.fragment_weather_list.*
import kotlinx.android.synthetic.main.fragment_weather_list.view.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.core.api.ApiFactory
import ru.egprojects.androidlab.core.services.LocationService
import ru.egprojects.androidlab.core.services.WeatherService
import kotlin.coroutines.CoroutineContext

class WeatherListFragment(
    private val onWeatherSelectedListener: OnWeatherSelectedListener,
    private val messageListener: MessageListener
) : Fragment(), CoroutineScope {

    private lateinit var service: WeatherService
    private val adapter = WeatherListAdapter(onWeatherSelectedListener)
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = ApiFactory.weatherService
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        updateData()
    }

    fun updateData() {
        LocationService.getLocation(context as Activity)
            .addOnCompleteListener {
                launch {
                    val data = it.result?.let {
                        service.weatherByLocation(it.longitude, it.latitude)
                    } ?: service.weatherByLocation()
                    withContext(Dispatchers.Main) {
                        adapter.submit(data.list)
                    }
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_weather_list, container, false)?.apply {
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar_weather_list)
        sv_weather_list.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                launch {
                    try {
                        val data = service.weatherByCity(query)
                        withContext(Dispatchers.Main) {
                            onWeatherSelectedListener.onWeatherSelected(data.id)
                        }
                    } catch (e: HttpException) {
                        withContext(Dispatchers.Main) {
                            messageListener.showMessage(getString(R.string.search_no_results))
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })
        rv_weather_list.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_weather_list, menu)
        val item = menu.findItem(R.id.action_search)
        sv_weather_list.setMenuItem(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }

}
