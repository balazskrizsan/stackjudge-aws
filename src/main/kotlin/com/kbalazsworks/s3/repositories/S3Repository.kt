package com.kbalazsworks.s3.repositories

import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.kbalazsworks.s3.factories.AmazonS3ClientFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class S3Repository(private val amazonS3ClientFactory: AmazonS3ClientFactory) {
    fun put(putObjectRequest: PutObjectRequest): PutObjectResult =
        amazonS3ClientFactory.create().putObject(putObjectRequest)
}
