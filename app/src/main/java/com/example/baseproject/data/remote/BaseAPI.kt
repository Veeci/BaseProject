package com.example.baseproject.data.remote

interface BaseAPI<T> {
    suspend fun insert(dto: T): T

    suspend fun insert(dtos: List<T>): List<T>

    suspend fun update(dto: T)

    suspend fun delete(dto: T)
}
