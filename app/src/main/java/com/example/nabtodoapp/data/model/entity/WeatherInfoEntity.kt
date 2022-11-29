package com.example.nabtodoapp.data.model.entity

import androidx.room.*
import com.example.nabtodoapp.data.model.BaseModel
import com.example.nabtodoapp.data.model.BaseModel.Companion.HOUR
import com.example.nabtodoapp.data.model.City
import com.example.nabtodoapp.data.model.DayInfo

@Entity(tableName = "WeatherInfo")
data class WeatherInfoEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "record_id") var id: Long = 0,
    @ColumnInfo(name = "cnt") var cnt: Int,
    @ColumnInfo(name = "cod") var cod: String,
    @ColumnInfo(name = "message") var message: Double,
    @ColumnInfo(name = "cache_timeout") var cacheTimeout: Long = (24 * HOUR).toLong(),
    @ColumnInfo(name = "timestamp") var timestamp: Long,
    @ColumnInfo(name = "city_id")  var cityId: Long
)