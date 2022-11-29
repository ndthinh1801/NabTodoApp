package com.example.nabtodoapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["day_id", "weather_desc_id"])
data class DayInfoWeatherDescCrossRef(
    @ColumnInfo(name = "day_id", index = true) var  dayId: Long,
    @ColumnInfo(name = "weather_desc_id", index = true) var weatherDescId: Long
)