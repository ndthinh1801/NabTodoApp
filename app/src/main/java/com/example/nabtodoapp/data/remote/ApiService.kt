package com.example.nabtodoapp.data.remote

import com.example.nabtodoapp.data.dto.WeatherDto
import com.example.nabtodoapp.data.model.WeatherItem
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(@QueryMap map: Map<String, String>): WeatherDto
}