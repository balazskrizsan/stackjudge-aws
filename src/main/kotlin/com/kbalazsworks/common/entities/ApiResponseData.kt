package com.kbalazsworks.common.entities

data class ApiResponseData(
    val data: Any? = null,
    val success: Boolean,
    val errorCode: Int,
    val requestId: String
)
