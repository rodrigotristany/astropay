package com.rodrigotristany.astropay.domain.usecases

import com.rodrigotristany.astropay.domain.entities.Location
import com.rodrigotristany.astropay.domain.entities.WeatherModel
import com.rodrigotristany.astropay.domain.models.ErrorHandler
import com.rodrigotristany.astropay.domain.repositories.WeatherRepository
import com.rodrigotristany.astropay.domain.usecases.base.UseCase
import javax.inject.Inject

class GetWeatherByPositionUseCase
@Inject constructor(private val weatherRepository: WeatherRepository,
                    errorHandler: ErrorHandler) : UseCase<WeatherModel, Location>(errorHandler) {
    override suspend fun executeOnBackground(location: Location?): WeatherModel = weatherRepository.getWeatherByPosition(location)
}