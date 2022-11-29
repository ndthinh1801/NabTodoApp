package com.example.nabtodoapp.data.model

import com.example.nabtodoapp.data.model.entity.DayInfoEntity

data class DayInfo(
    val clouds: Double,
    val deg: Double,
    val dt: Long,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val speed: Double,
    val sunrise: Double,
    val sunset: Double,
    val temp: Temp,
    val weather: List<Weather>
)

fun DayInfo.toEntity(weatherRecordId: Long): DayInfoEntity {
    return DayInfoEntity(
        clouds = clouds,
        deg = deg,
        dt = dt,
        feels_like = feels_like,
        gust = gust,
        humidity = humidity,
        pop = pop,
        pressure = pressure,
        rain = rain,
        speed = speed,
        sunrise = sunrise,
        sunset = sunset,
        temp = temp,
        weatherRecordId = weatherRecordId
    )
}