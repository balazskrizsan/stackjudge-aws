package com.kbalazsworks.stackjudge.domain.aws_module.value_objects

import com.amazonaws.services.s3.model.PutObjectResult

data class CdnServicePutResponse(
    val putObjectResult: PutObjectResult,
    val path: String,
    val fileName: String
)
