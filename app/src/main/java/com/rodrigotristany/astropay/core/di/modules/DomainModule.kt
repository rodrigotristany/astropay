package com.rodrigotristany.astropay.core.di.modules

import com.rodrigotristany.astropay.domain.models.ErrorHandler
import com.rodrigotristany.astropay.domain.repositories.WeatherRepository
import com.rodrigotristany.astropay.domain.usecases.GetWeatherByCityUseCase
import com.rodrigotristany.astropay.domain.usecases.GetWeatherByPositionUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideGetWeatherByCityUseCase(weatherRepository: WeatherRepository,
                                       errorHandler: ErrorHandler) : GetWeatherByCityUseCase =
        GetWeatherByCityUseCase(weatherRepository, errorHandler)

    @Provides
    @Singleton
    fun provideGetWeatherByPositionUseCase(weatherRepository: WeatherRepository,
                                           errorHandler: ErrorHandler): GetWeatherByPositionUseCase =
        GetWeatherByPositionUseCase(weatherRepository, errorHandler)

}