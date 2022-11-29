package com.example.nabtodoapp.data.model.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nabtodoapp.data.model.City
import com.example.nabtodoapp.data.model.DayInfo
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.data.model.entity.*

data class WeatherInfoAndDayInfo(
    @Embedded var weatherInfoEntity: WeatherInfoEntity,
    @Relation(
        entity = DayInfoEntity::class,
        parentColumn = "record_id",
        entityColumn = "weather_record_id",
    )
    var list: List<DayInfoAndWeatherDesc>,
//    @Relation(
//        parentColumn = "city_id",
//        entityColumn = "id"
//    )
//    var city: CityEntity
)