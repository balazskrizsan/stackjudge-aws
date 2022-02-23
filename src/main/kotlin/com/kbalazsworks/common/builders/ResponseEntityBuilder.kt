package com.kbalazsworks.common.builders

import com.fasterxml.jackson.databind.ObjectMapper
import com.kbalazsworks.common.entities.ApiResponseData
import org.apache.http.HttpStatus
import javax.ws.rs.core.Response

class ResponseEntityBuilder {
    private var data: Any? = null
    private var errorCode: Int = 0
    private var statusCode: Int = HttpStatus.SC_OK
//    private var headers: HttpHeaders = HttpHeadersImpl(emptyList()) @todo: implement

    fun data(data: Any): ResponseEntityBuilder {
        this.data = data

        return this
    }

    fun errorCode(errorCode: Int): ResponseEntityBuilder {
        this.errorCode = errorCode

        return this
    }

    fun statusCode(statusCode: Int): ResponseEntityBuilder {
        this.statusCode = statusCode

        return this
    }

    fun build(): Response? {
        val success = errorCode == 0
        if (errorCode > 0 && statusCode == HttpStatus.SC_OK) {
//            throw ApiException("Status code setup is needed for error response") @todo: implement
        }
        val apiResponseData = ApiResponseData(data, success, errorCode, "1")

        return Response.ok(ObjectMapper().writeValueAsString(apiResponseData)).status(300).build()
    }
}
