package com.example.baseproject.data

import com.example.baseproject.data.local.BaseEntity

open class BaseModel(
    open var id: String? = null,
)

fun BaseModel.mapToEntity(): BaseEntity {
    return BaseEntity(id = this.id)
}
