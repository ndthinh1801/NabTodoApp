package com.example.nabtodoapp.data.model.entity.pojo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.nabtodoapp.data.model.DayInfo
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.data.model.entity.CityEntity
import com.example.nabtodoapp.data.model.entity.toItem

data class CityAndWeatherInfo(
    @Embedded var weatherInfoAndDayInfo: WeatherInfoAndDayInfo,
    @Relation(
        entity = CityEntity::class,
        parentColumn = "city_id",
        entityColumn = "id"
    )
    var city: CityEntity
)


fun CityAndWeatherInfo.toItem(): WeatherItem {
    weatherInfoAndDayInfo.apply {
        val dayInfoList = ArrayList<DayInfo>()
        list.forEach {
            dayInfoList.add(it.toItem())
        }
        weatherInfoEntity.apply {
            val weatherItem = WeatherItem(
                city = city.toItem(),
                cnt = cnt,
                cod = cod,
                message = message,
                cacheTimeout = cacheTimeout,
                list = dayInfoList
            )
            weatherItem.timestamp = timestamp
            return weatherItem
        }
    }
}


