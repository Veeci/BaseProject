package com.example.baseproject.domain

import com.example.baseproject.data.BaseMapper
import com.example.baseproject.data.BaseRepository
import com.example.baseproject.data.local.LocalDataSource
import com.example.baseproject.data.remote.RemoteDataSource

class GenericRepository<BaseEntity, BaseModel>(
    private val localDataSource: LocalDataSource<BaseEntity>,
    private val remoteDataSource: RemoteDataSource<BaseModel>,
    private val localMapper: BaseMapper<BaseEntity, BaseModel>,
    private val remoteMapper: BaseMapper<BaseModel, BaseEntity>,
) : BaseRepository<BaseModel> {
    override suspend fun insert(model: BaseModel): BaseModel {
        TODO("Not yet implemented")
    }

    override suspend fun insert(models: List<BaseModel>): List<BaseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun update(model: BaseModel) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(model: BaseModel) {
        TODO("Not yet implemented")
    }
}
