package com.example.nabtodoapp.data.model

import com.example.nabtodoapp.data.model.entity.DayInfoEntity
import com.example.nabtodoapp.data.model.entity.WeatherDescEntity

data class Weather(
    var id: Long,
    val description: String,
    val icon: String,
    val main: String
)

fun Weather.toEntity(): WeatherDescEntity {
    return WeatherDescEntity(
        id, description, icon, main
    )
}