package com.example.nabtodoapp.domain.get_weather_by_city

import com.example.nabtodoapp.data.Result
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        query: String, numberForecastDay: Int,
        units: String, appId: String
    ): Result<WeatherItem> {
        return repository.getWeather(query, numberForecastDay, units, appId)
    }
}