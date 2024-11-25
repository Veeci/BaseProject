package com.example.baseproject.data.remote

class RemoteDataSource<BaseModel>(private val api: BaseAPI<BaseModel>) {
    suspend fun insert(dto: BaseModel): BaseModel = api.insert(dto)

    suspend fun insert(dtos: List<BaseModel>) = api.insert(dtos)

    suspend fun update(dto: BaseModel) {
        api.update(dto)
    }

    suspend fun delete(dto: BaseModel) {
        api.delete(dto)
    }
}
