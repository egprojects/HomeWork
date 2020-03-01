package ru.egprojects.androidlab

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import ru.egprojects.androidlab.ui.weather_details.WeatherDetailsFragment
import ru.egprojects.androidlab.ui.weather_list.MessageListener
import ru.egprojects.androidlab.ui.weather_list.OnWeatherSelectedListener
import ru.egprojects.androidlab.ui.weather_list.WeatherListFragment

class MainActivity : AppCompatActivity(),
    CoroutineScope by MainScope(), OnWeatherSelectedListener, MessageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = WeatherListFragment(this, this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            (fragment as? WeatherListFragment)?.updateData()
        }
    }

    override fun onWeatherSelected(weatherId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                WeatherDetailsFragment.newInstance(weatherId)
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun showMessage(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_INDEFINITE
        ).show()
    }

}
