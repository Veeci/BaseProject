package com.example.baseproject.data.local

interface LocalDataSource<E : BaseEntity> {
    suspend fun insert(entity: E)

    suspend fun insert(entities: List<E>)

    suspend fun getAll(): List<E>

    suspend fun update(entity: E)

    suspend fun delete(entity: E)

    suspend fun findByID(id: String): E?
}
