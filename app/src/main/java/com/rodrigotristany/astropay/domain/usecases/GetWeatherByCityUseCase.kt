package com.rodrigotristany.astropay.domain.usecases

import com.rodrigotristany.astropay.domain.entities.WeatherModel
import com.rodrigotristany.astropay.domain.models.ErrorHandler
import com.rodrigotristany.astropay.domain.repositories.WeatherRepository
import com.rodrigotristany.astropay.domain.usecases.base.UseCase
import javax.inject.Inject

class GetWeatherByCityUseCase
@Inject constructor(private val weatherRepository: WeatherRepository,
                    errorHandler: ErrorHandler) : UseCase<WeatherModel,String>(errorHandler) {
    override suspend fun executeOnBackground(city: String?): WeatherModel = weatherRepository.getWeatherByCity(city!!)
}