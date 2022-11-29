package com.example.nabtodoapp.data.local

import com.example.nabtodoapp.data.Result
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.data.model.entity.DayInfoWeatherDescCrossRef
import com.example.nabtodoapp.data.model.entity.WeatherDescEntity
import com.example.nabtodoapp.data.model.entity.WeatherInfoEntity
import com.example.nabtodoapp.data.model.entity.pojo.toItem
import com.example.nabtodoapp.data.model.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class LocalService @Inject constructor(
    private val weatherItemDao: WeatherItemDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insertWeatherItem(item: WeatherItem) {
        item.run {
            val weatherInfoEntity = item.toEntity(item.city.id)
            val weatherDescEntities = ArrayList<WeatherDescEntity>()
            val dayInfoWeatherDescCrossRef = ArrayList<DayInfoWeatherDescCrossRef>()
            val weatherRecordId = weatherItemDao.insertWeatherItem(weatherInfoEntity)
            item.list.mapIndexed { _, dayInfo ->
                dayInfo.toEntity(weatherRecordId)
            }
            item.list.forEach { dayInfo ->
                val dayInfoId = weatherItemDao.insertDayInfo(dayInfo.toEntity(weatherRecordId))
                dayInfo.weather.forEach {
                    weatherDescEntities.add(it.toEntity())
                    dayInfoWeatherDescCrossRef.add(DayInfoWeatherDescCrossRef(dayInfoId, it.id))
                }
            }
            weatherItemDao.insertWeatherInfo(
                item.city.toEntity(),
                weatherDescEntities,
                dayInfoWeatherDescCrossRef
            )
        }
    }

    suspend fun getWeathers(cityName: String): Result<WeatherItem> = withContext(ioDispatcher) {
        try {
            val item = weatherItemDao.getWeatherItemByCityName(cityName)?.toItem()
            if (item != null) {
                return@withContext Result.Success(item)
            } else {
                return@withContext Result.Error(Exception("Weather Item not found"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }
}