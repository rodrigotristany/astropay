package com.rodrigotristany.astropay.data.api

import com.rodrigotristany.astropay.domain.entities.Location
import com.rodrigotristany.astropay.domain.entities.WeatherModel
import com.rodrigotristany.astropay.domain.repositories.WeatherRepository
import javax.inject.Inject

class WeatherService @Inject constructor(
    private val weatherApi: WeatherApi) : WeatherRepository  {

    override suspend fun getWeatherByCity(city: String?): WeatherModel {
        var weatherInfo = weatherApi.getWeatherByCityAsync(city!!)
        return weatherInfo.toEntity()
    }

    override suspend fun getWeatherByPosition(location: Location?) : WeatherModel {
        var weatherInfo = weatherApi.getWeatherByPositionAsync(location!!.lat, location!!.long)
        return weatherInfo.toEntity()
    }
}