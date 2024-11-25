package com.example.baseproject.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T): T

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<T>): List<T>

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)
}
