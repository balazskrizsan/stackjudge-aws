package com.kbalazsworks.stackjudge_aws.test_helpers

import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository
import org.mockito.Mockito
import org.mockito.kotlin.argThat

class S3RepositoryMocker {
    companion object {
        fun put_returns_PutObjectResult(
            whenPut: PutObjectRequest,
            thanReturns: PutObjectResult
        ): S3Repository {
            val mock = MockCreator.getS3RepositoryMock();

            Mockito.`when`(
                mock.put(argThat
                {
                    this.bucketName == whenPut.bucketName
                            && key == whenPut.key
                            && inputStream.readAllBytes().contentEquals(whenPut.inputStream.readAllBytes())
                            && metadata.contentLength == whenPut.metadata.contentLength
                })
            )
                .thenReturn(thanReturns)

            return mock
        }
    }
}
