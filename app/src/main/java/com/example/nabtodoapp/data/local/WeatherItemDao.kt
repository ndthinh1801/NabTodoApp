package com.example.nabtodoapp.data.local

import androidx.room.*
import com.example.nabtodoapp.data.model.entity.*
import com.example.nabtodoapp.data.model.entity.pojo.CityAndWeatherInfo

/**
 * The Data Access Object for the Item to Sell class.
 */
@Dao
interface WeatherItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(
        city: CityEntity,
        weatherDescEntities: List<WeatherDescEntity>,
        dayInfoWeatherDescCrossRefs: List<DayInfoWeatherDescCrossRef>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayInfo(dayInfoEntity: DayInfoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherItem(weatherInfoEntity: WeatherInfoEntity): Long

    @Transaction
    @Query("select * from City join WeatherInfo where City.name = :name and timestamp = (select MAX(timestamp) from WeatherInfo)")
    suspend fun getWeatherItemByCityName(name: String): CityAndWeatherInfo?
}