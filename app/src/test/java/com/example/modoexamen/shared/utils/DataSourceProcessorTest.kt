package com.example.modoexamen.shared.utils

import com.example.modoexamen.shared.model.ErrorCodes
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ResponseHandlingTest {

    @Test
    fun `handleSuccess should set result properties correctly for successful response`() {
        val gson = Gson()
        val responseBody = "{ \"key\": \"value\" }"
        val expectedJson = "{key=value}"
        val successResponse = Response.success(gson.fromJson(responseBody, Any::class.java))
        val result = mockk<ResponseResult<Any>>(relaxed = true)
        val expectedObject = gson.fromJson(expectedJson, Any::class.java)
        every { result.isSuccessful } returns true
        every { result.error } returns ""
        every { result.internalError } returns null
        every { result.response } returns expectedObject

        handleSuccess(successResponse, result)

        assertEquals(successResponse.isSuccessful, result.isSuccessful)
        assert(result.error.isBlank())
        assertNull(result.internalError)
        assertEquals(successResponse.body(), result.response)
    }

    @Test
    fun `handleSuccess should set result properties correctly for unsuccessful response`() {
        val gson = Gson()
        val errorResponseBody = "{ \"internalCode\": 500 }"
        val errorResponse = Response.error<Any>(500, errorResponseBody.toResponseBody())
        val result = mockk<ResponseResult<Any>>(relaxed = true)
        every { result.isSuccessful } returns false
        every { result.error } returns "Some Error"
        every { result.internalError } returns null
        every { result.response } returns null

        handleSuccess(errorResponse, result)

        assertEquals(errorResponse.isSuccessful, result.isSuccessful)
        assertNotNull(result.error)
        assertEquals(gson.fromJson(errorResponseBody, ErrorResponse::class.java).internalCode, result.internalError)
        assertNull(result.response)
    }

    @Test
    fun `handleError should set result properties correctly for HttpException`() {
        val gson = Gson()
        val errorResponseBody = "{ \"internal_code\": \"authentication_fail\" }"
        val httpException = HttpException(Response.error<Any>(404, errorResponseBody.toResponseBody()))

        val result = mockk<ResponseResult<Any>>(relaxed = true)
        every { result.isSuccessful } returns false
        every { result.error } returns "Some Error"
        every { result.internalError } returns ErrorCodes.authentication_fail
        every { result.response } returns null

        handleError(httpException, result)

        assertNull(result.response)
        assertNotNull(result.error)
        assertEquals(gson.fromJson(errorResponseBody, ErrorResponse::class.java).internalCode, result.internalError)
    }

    @Test
    fun `handleError should set result properties correctly for other exceptions`() {
        val exceptionMessage = "Some unexpected error"
        val exception = Exception(exceptionMessage)
        val result = mockk<ResponseResult<Any>>(relaxed = true)
        every { result.isSuccessful } returns false
        every { result.error } returns exceptionMessage
        every { result.internalError } returns null
        every { result.response } returns null

        handleError(exception, result)

        assertNull(result.response)
        assertEquals(exceptionMessage, result.error)
        assertNull(result.internalError)
    }
}
