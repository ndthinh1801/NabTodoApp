package com.example.nabtodoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.data.model.entity.*

@Database(
    entities = [WeatherInfoEntity::class, DayInfoEntity::class, WeatherDescEntity::class, CityEntity::class, DayInfoWeatherDescCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun weatherItemDao(): WeatherItemDao

}