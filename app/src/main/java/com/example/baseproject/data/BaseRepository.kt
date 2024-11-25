package com.example.baseproject.data

interface BaseRepository<Model> {
    suspend fun insert(model: Model): Model

    suspend fun insert(models: List<Model>): List<Model>

    suspend fun update(model: Model)

    suspend fun delete(model: Model)
}
