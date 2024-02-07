package com.example.modoexamen.shared.utils

import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

inline fun <reified T : Any> handleSuccess(response: Response<T>, result: ResponseResult<T>) {
    val gson = Gson()
    result.isSuccessful = response.isSuccessful
    if(response.isSuccessful) {
        result.response = response.body()
    } else {
        val errorBody = response.errorBody()?.string()
        if (errorBody != null) {
            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
            result.internalError = errorResponse.internalCode
        }
    }
}

inline fun <reified T : Any> handleError(exception: Exception, result: ResponseResult<T>) {
    val gson = Gson()
    when(exception){
        is HttpException -> {
            val errorBody = exception.response()?.errorBody()?.string()
            if(errorBody != null) {
                val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                result.internalError = errorResponse.internalCode
            } else {
                result.error = exception.message.toString()
            }
        } else -> {
        result.error = exception.message.toString()
    }
    }
}
