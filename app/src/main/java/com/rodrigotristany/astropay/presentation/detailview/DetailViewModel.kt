package com.rodrigotristany.astropay.presentation.detailview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
                var tempList = mutableListOf<DetailModel>()
                tempList.add(DetailModel("Temperatura actual", "${it.temp} Cº"))
                tempList.add(DetailModel("Temperatura mínima", "${it.minTemp} Cº"))
                tempList.add(DetailModel("Temperatura máxima", "${it.maxTemp} Cº"))
                tempList.add(DetailModel("Viento", "${it.wind} k/h"))
                tempList.add(DetailModel("Humedad", "${it.humidity}%"))
                weatherModelList.value = tempList
            }

            onError { throwable ->
                error.value = throwable
            }

            onCancel {}
        }
    }

    fun weatherInfoByPosition() {

    }

    override fun onCleared() {
        super.onCleared()
        getWeatherByPositionUseCase.unsubscribe()
        getWeatherByCityUseCase.unsubscribe()
    }
}