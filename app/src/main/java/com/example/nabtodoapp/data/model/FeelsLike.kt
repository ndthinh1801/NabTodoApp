package com.example.nabtodoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class FeelsLike(
    @ColumnInfo(name = "day") val day: Double,
    @ColumnInfo(name = "eve") val eve: Double,
    @ColumnInfo(name = "morn") val morn: Double,
    @ColumnInfo(name = "night") val night: Double
)