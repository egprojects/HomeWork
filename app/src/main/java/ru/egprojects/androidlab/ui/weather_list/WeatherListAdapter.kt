package ru.egprojects.androidlab.ui.weather_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather.view.*
import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.core.models.CityWeather

class WeatherListAdapter(
    private val onWeatherSelectedListener: OnWeatherSelectedListener
) : RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {

    private var weatherList: List<CityWeather>? = null

    fun submit(data: List<CityWeather>) {
        weatherList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_weather,
                parent,
                false
            )
    )

    override fun getItemCount() = weatherList?.size ?: 0

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList!![position])
    }

    inner class WeatherViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(weather: CityWeather) = itemView.apply {
            setOnClickListener {
                onWeatherSelectedListener.onWeatherSelected(weather.id)
            }
            tv_city.text = weather.name
            tv_temperature.apply {
                text = context.getString(R.string.temperature, weather.main.temp.toString())
                setTextColor(colorForCentigrade(weather.main.temp))
            }
            Glide.with(iv_weather_icon)
                .load(weather.weathers[0].getIcon())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv_weather_icon)
        }

        private fun colorForCentigrade(centigrade: Double) = when {
            centigrade >= 30 -> Color.RED
            centigrade in 10.0..20.0 -> Color.YELLOW
            centigrade in -10.0..10.0 -> Color.GREEN
            centigrade in -20.0..-10.0 -> Color.CYAN
            else -> Color.BLUE
        }

    }

}