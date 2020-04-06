package ru.egprojects.androidlab.core.models

import com.google.gson.annotations.SerializedName

data class WeatherList(
    @SerializedName("message")
    val message: String,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("list")
    val list: List<CityWeather>
)
