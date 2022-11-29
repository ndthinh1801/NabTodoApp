package com.example.nabtodoapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nabtodoapp.data.model.Weather

@Entity(tableName = "WeatherDesc")
data class WeatherDescEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "weather_desc_id") var weatherDescId: Long,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "icon") var icon: String,
    @ColumnInfo(name = "main") var main: String)

fun WeatherDescEntity.toItem() : Weather {
    return Weather(weatherDescId, description, icon, main)
}