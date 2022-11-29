package com.example.nabtodoapp.data.remote

import com.example.nabtodoapp.data.Result
import com.example.nabtodoapp.data.dto.toItem
import com.example.nabtodoapp.data.model.WeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkService @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getWeather(
        query: String, numberForecastDay: Int,
        units: String, appId: String
    ): Result<WeatherItem> {
        return try {
            val queryMap = HashMap<String, String>()
            queryMap["q"] = query
            queryMap["cnt"] = numberForecastDay.toString()
            queryMap["units"] = units
            queryMap["appid"] = appId
            val result = apiService.getWeather(queryMap).toItem()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}