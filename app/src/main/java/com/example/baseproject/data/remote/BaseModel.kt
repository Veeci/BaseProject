package com.example.baseproject.data.remote

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
open class BaseModel(
    @field:SerializedName("id")
    open val id: Long? = null,
) : Parcelable
