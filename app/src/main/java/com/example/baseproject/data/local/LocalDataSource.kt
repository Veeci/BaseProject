package com.example.baseproject.data.local

class LocalDataSource<BaseEntity>(private val dao: BaseDAO<BaseEntity>) {
    suspend fun insert(entity: BaseEntity) = dao.insert(entity)

    suspend fun insert(entities: List<BaseEntity>) = dao.insert(entities)

    suspend fun update(entity: BaseEntity) = dao.update(entity)

    suspend fun delete(entity: BaseEntity) = dao.delete(entity)
}
