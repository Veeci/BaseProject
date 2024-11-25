package com.example.baseproject.data

interface BaseMapper<BaseEntity, BaseModel> {
    fun mapFromEntity(entity: BaseEntity): BaseModel

    fun mapToEntity(model: Any?): BaseEntity
}
