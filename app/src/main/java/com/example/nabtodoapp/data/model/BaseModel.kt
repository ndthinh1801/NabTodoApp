package com.example.nabtodoapp.data.model

import androidx.room.ColumnInfo

abstract class BaseModel {
    @ColumnInfo(name = "timestamp") var timestamp: Long

    constructor() {
        timestamp = System.currentTimeMillis()
    }

    constructor(timeStamp: Long) {
        timestamp = timeStamp
    }

    abstract val isUpToDate: Boolean

    companion object {
        const val MIN = 60 * 1000
        const val HOUR = 60 * MIN
    }
}