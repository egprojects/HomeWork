package ru.egprojects.androidlab.ui.weather_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_weather_details.*
import kotlinx.coroutines.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.core.api.ApiFactory
import ru.egprojects.androidlab.core.models.CityWeather
import kotlin.coroutines.CoroutineContext

class WeatherDetailsFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments == null)
            throw IllegalArgumentException("No data passed to WeatherDetailsFragment")
        launch {
            val data = ApiFactory.weatherService.weatherById(
                arguments!!.getInt(ARG_WEATHER_ID)
            )
            withContext(Dispatchers.Main) {
                bind(data)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_weather_details, container, false)

    private fun bind(data: CityWeather) {
        tv_weather_temperature.text = getString(R.string.temperature, data.main.temp.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }

    companion object {
        private const val ARG_WEATHER_ID = "weather_id"

        fun newInstance(weatherId: Int) = WeatherDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_WEATHER_ID, weatherId)
            }
        }
    }

}
