package com.example.nabtodoapp.data.model.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nabtodoapp.data.model.DayInfo
import com.example.nabtodoapp.data.model.Weather
import com.example.nabtodoapp.data.model.entity.DayInfoEntity
import com.example.nabtodoapp.data.model.entity.DayInfoWeatherDescCrossRef
import com.example.nabtodoapp.data.model.entity.WeatherDescEntity
import com.example.nabtodoapp.data.model.entity.toItem

data class DayInfoAndWeatherDesc(
    @Embedded var dayInfoEntity: DayInfoEntity,
    @Relation(
        parentColumn = "day_id",
        entityColumn = "weather_desc_id",
        associateBy = Junction(DayInfoWeatherDescCrossRef::class)
    )
    var weather: List<WeatherDescEntity>
)

fun DayInfoAndWeatherDesc.toItem() : DayInfo {
    dayInfoEntity.apply {
        val weatherList = ArrayList<Weather>()
        weather.forEach {
                weatherList.add(it.toItem())
        }
        return DayInfo(
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
            weather = weatherList
        )
    }
}