package com.kbalazsworks.stackjudge_aws.test_helpers

import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.doAnswer

class S3RepositoryMocker {
    companion object {
        fun put_returns_PutObjectResult(
            whenPut: PutObjectRequest,
            thanReturns: PutObjectResult
        ): S3Repository {
            val mock = MockCreator.getS3RepositoryMock()

            `when`(
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

        fun put_returns_exception(): S3Repository {
            val mock = MockCreator.getS3RepositoryMock()

            doAnswer { throw Exception("Bad Request") }.`when`(mock).put(any())

            return mock
        }
    }
}
