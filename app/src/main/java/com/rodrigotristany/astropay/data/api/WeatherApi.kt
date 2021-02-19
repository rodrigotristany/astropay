package com.rodrigotristany.astropay.data.api

import com.rodrigotristany.astropay.data.models.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather?appid=b497612441effeb3cce53157c6f4fc0c")
    suspend fun getWeatherByCityAsync(@Query("q") q: String): WeatherInfo

    @GET("data/2.5/weather?appid=b497612441effeb3cce53157c6f4fc0c")
    suspend fun getWeatherByPositionAsync(
        @Query("lat") lat: Double,
        @Query("lon") long: Double): WeatherInfo
}