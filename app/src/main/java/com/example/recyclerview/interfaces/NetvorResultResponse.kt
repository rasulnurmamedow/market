package com.example.recyclerview.interfaces

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

object NetworkResultHandler{
    fun getErrorResponse(response:String):ErrorResponse{
        return Gson().fromJson(response, ErrorResponse::class.java)
    }
}

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String? = null, val errorResponse: ErrorResponse? = null) : Resource<Nothing>()
}


data class ErrorResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val message: String?
)
