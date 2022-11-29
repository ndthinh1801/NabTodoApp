package com.example.nabtodoapp.data.model

import com.example.nabtodoapp.data.model.entity.CityEntity
import com.example.nabtodoapp.data.model.entity.WeatherDescEntity

data class City(
    val id: Long,
    val coord: Coord,
    val country: String,
    val name: String,
    val population: Int,
    val timezone: Int
)

fun City.toEntity() : CityEntity {
    return CityEntity(
        id, coord, country, name, population, timezone
    )
}