package com.rodrigotristany.astropay.domain.entities

data class WeatherModel(
    val temp : Double,
    val minTemp : Double,
    val maxTemp : Double,
    val wind : Double,
    val humidity : Int
)