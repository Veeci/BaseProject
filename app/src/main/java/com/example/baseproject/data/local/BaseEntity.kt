package com.example.baseproject.data.local

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

open class BaseEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String? = null
}
