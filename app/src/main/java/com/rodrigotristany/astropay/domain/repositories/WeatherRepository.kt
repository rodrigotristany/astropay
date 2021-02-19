package com.rodrigotristany.astropay.domain.repositories

import com.rodrigotristany.astropay.domain.entities.Location
import com.rodrigotristany.astropay.domain.entities.WeatherModel

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String?) : WeatherModel
    suspend fun getWeatherByPosition(location: Location?) : WeatherModel
}