package com.rodrigotristany.astropay.presentation.detailview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.rodrigotristany.astropay.domain.entities.Location
import com.rodrigotristany.astropay.domain.entities.WeatherModel
import com.rodrigotristany.astropay.domain.models.ErrorModel
import com.rodrigotristany.astropay.domain.usecases.GetWeatherByCityUseCase
import com.rodrigotristany.astropay.domain.usecases.GetWeatherByPositionUseCase
import javax.inject.Inject

class DetailViewModel
@Inject constructor(
    private val getWeatherByPositionUseCase: GetWeatherByPositionUseCase,
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase) : ViewModel(){

    val error : MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val weatherModelList : MutableLiveData<MutableList<DetailModel>> by lazy { MutableLiveData<MutableList<DetailModel>>() }

    fun weatherInfoByCity(city: String) {
        getWeatherByCityUseCase.execute(city) {
            onComplete {
                updarteWeatherInfo(it)
            }

            onError { throwable ->
                error.value = throwable
            }

            onCancel {}
        }
    }

    fun weatherInfoByPosition(location: Location) {
        getWeatherByPositionUseCase.execute(location) {
            onComplete {
                updarteWeatherInfo(it)
            }

            onError { throwable ->
                error.value = throwable
            }

            onCancel {}
        }
    }

    private fun updarteWeatherInfo(weatherModel: WeatherModel) {
        var tempList = mutableListOf<DetailModel>()
        tempList.add(DetailModel("Temperatura actual", "${weatherModel.temp} Cº"))
        tempList.add(DetailModel("Temperatura mínima", "${weatherModel.minTemp} Cº"))
        tempList.add(DetailModel("Temperatura máxima", "${weatherModel.maxTemp} Cº"))
        tempList.add(DetailModel("Viento", "${weatherModel.wind} k/h"))
        tempList.add(DetailModel("Humedad", "${weatherModel.humidity}%"))
        weatherModelList.value = tempList
    }

    override fun onCleared() {
        super.onCleared()
        getWeatherByPositionUseCase.unsubscribe()
        getWeatherByCityUseCase.unsubscribe()
    }
}