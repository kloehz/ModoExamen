package com.example.modoexamen.shared.model

class ResponseResult<T> {

    companion object {
        const val HTTP_UNDEFINED = 0
    }

    var isSuccessful: Boolean = false
    var response: T? = null
    var code: Int = HTTP_UNDEFINED
    var error: String = ""
    var internalError: ErrorCodes? = null
}
