package com.example.baseproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
