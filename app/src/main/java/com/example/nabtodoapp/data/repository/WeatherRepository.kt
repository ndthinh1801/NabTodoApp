package com.example.nabtodoapp.data.repository

import com.example.nabtodoapp.data.Result
import com.example.nabtodoapp.data.local.LocalService
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.data.remote.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val networkService: NetworkService,
    private val localService: LocalService
) {
    suspend fun getWeather(
        query: String, numberForecastDay: Int,
        units: String, appId: String
    ): Result<WeatherItem> {
        return withContext(Dispatchers.IO) {
            val dataFromLocal = localService.getWeathers(query)
            return@withContext if (dataFromLocal is Result.Success && dataFromLocal.data.isUpToDate) {
                dataFromLocal
            } else {
                getWeatherFromNetwork(query, numberForecastDay, units, appId)
            }
        }
    }

    private suspend fun getWeatherFromNetwork(
        query: String, numberForecastDay: Int,
        units: String, appId: String
    ): Result<WeatherItem> {
        val dataFromNetwork = networkService.getWeather(query, numberForecastDay, units, appId)
        if (dataFromNetwork is Result.Success) {
            localService.insertWeatherItem(dataFromNetwork.data)
        } else if (dataFromNetwork is Result.Error) {
            dataFromNetwork.exception
        }
        return dataFromNetwork
    }
}