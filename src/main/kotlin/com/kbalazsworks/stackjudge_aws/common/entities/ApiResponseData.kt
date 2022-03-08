package com.kbalazsworks.stackjudge_aws.common.entities

data class ApiResponseData<T> (
    var data: T? = null,
    var success: Boolean,
    var errorCode: Int,
    var requestId: String
)
