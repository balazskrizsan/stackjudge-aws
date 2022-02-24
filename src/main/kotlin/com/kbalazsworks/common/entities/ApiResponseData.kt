package com.kbalazsworks.common.entities

data class ApiResponseData<T> (
    var data: T? = null,
    var success: Boolean,
    var errorCodeerrorCode: Int,
    var requestId: String
)

