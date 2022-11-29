package com.example.nabtodoapp.data.dto

import com.example.nabtodoapp.data.model.City
import com.example.nabtodoapp.data.model.DayInfo
import com.example.nabtodoapp.data.model.WeatherItem

data class WeatherDto(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DayInfo>,
    val message: Double
)

fun WeatherDto.toItem() : WeatherItem {
    return WeatherItem(
        city = city,
        cnt = cnt,
        cod = cod,
        list = list,
        message = message
    )
}