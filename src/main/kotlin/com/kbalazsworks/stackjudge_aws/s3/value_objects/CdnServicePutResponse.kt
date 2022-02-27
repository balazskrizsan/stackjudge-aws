package com.kbalazsworks.stackjudge_aws.s3.value_objects

data class CdnServicePutResponse(
    val path: String,
    val fileName: String,
    val s3eTag: String,
    val s3contentMd5: String
)
