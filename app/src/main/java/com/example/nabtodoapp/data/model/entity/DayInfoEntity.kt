package com.example.nabtodoapp.data.model.entity

import androidx.room.*
import com.example.nabtodoapp.data.model.City
import com.example.nabtodoapp.data.model.FeelsLike
import com.example.nabtodoapp.data.model.Temp

@Entity(tableName = "DayInfo")
data class DayInfoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "day_id") var dayId: Long = 0,
    @ColumnInfo(name = "clouds") var clouds: Double,
    @ColumnInfo(name = "deg") var deg: Double,
    @ColumnInfo(name = "dt") var dt: Long,
    @Embedded(prefix = "feels_like_") var feels_like: FeelsLike,
    @ColumnInfo(name = "gust") var gust: Double,
    @ColumnInfo(name = "humidity") var humidity: Double,
    @ColumnInfo(name = "pop") var pop: Double,
    @ColumnInfo(name = "pressure") var pressure: Double,
    @ColumnInfo(name = "rain") var rain: Double,
    @ColumnInfo(name = "speed") var speed: Double,
    @ColumnInfo(name = "sunrise") var sunrise: Double,
    @ColumnInfo(name = "sunset") var sunset: Double,
    @Embedded(prefix = "temp_") var temp: Temp,
    @ColumnInfo(name = "weather_record_id")  var weatherRecordId: Long
)