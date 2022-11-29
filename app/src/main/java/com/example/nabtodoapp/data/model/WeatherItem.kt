package com.example.nabtodoapp.data.model

import com.example.nabtodoapp.data.dto.WeatherDto
import com.example.nabtodoapp.data.model.entity.WeatherInfoEntity

data class WeatherItem(
    val city: City,
    val cnt: Int,
    val cod: String,
    val message: Double,
    val cacheTimeout: Long = (24 * HOUR).toLong(),
    val list: List<DayInfo>
) : BaseModel() {

    override val isUpToDate: Boolean
        get() = System.currentTimeMillis() - timestamp < cacheTimeout
}

fun WeatherItem.toEntity(cityId: Long): WeatherInfoEntity {
    return WeatherInfoEntity(
            cityId = cityId,
            cnt = cnt,
            cod = cod,
            message = message,
            cacheTimeout = cacheTimeout,
            timestamp = timestamp
        )
}