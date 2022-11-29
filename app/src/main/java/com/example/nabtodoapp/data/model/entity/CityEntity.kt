package com.example.nabtodoapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nabtodoapp.data.model.City
import com.example.nabtodoapp.data.model.Coord

@Entity(tableName = "City")
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long,
    @Embedded var coord: Coord,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "population") var population: Int,
    @ColumnInfo(name = "timezone") var timezone: Int,
)

fun CityEntity.toItem() : City {
    return City(
        id, coord, country, name, population, timezone
    )
}