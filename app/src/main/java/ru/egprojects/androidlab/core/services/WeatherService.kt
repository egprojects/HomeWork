package ru.egprojects.androidlab.core.services

import retrofit2.http.GET
import retrofit2.http.Query
import ru.egprojects.androidlab.core.models.CityWeather
import ru.egprojects.androidlab.core.models.WeatherList

interface WeatherService {

    @GET("weather")
    suspend fun weatherByCity(@Query("q") city: String): CityWeather

    @GET("find")
    suspend fun weatherByLocation(
        @Query("lon") longitude: Double = 37.62,
        @Query("lat") latitude: Double = 55.75,
        @Query("cnt") count: Int = 10
    ): WeatherList

    @GET("weather")
    suspend fun weatherById(@Query("id") id: Int): CityWeather

}
