package com.kbalazsworks.stackjudge_aws.s3.entities

import java.util.UUID

data class RemoteFile(
    val id: UUID?,
    val path: String,
    val filename: String,
    val s3ETag: String,
    val s3ContentMd5: String
)