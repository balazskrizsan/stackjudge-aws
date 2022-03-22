package com.kbalazsworks.stackjudge_aws.test_helpers

import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService
import org.mockito.Mockito

class ApplicationPropertiesServiceMocker {
    companion object {
        const val AWS_ACCESS_KEY = "aws_access_key";
        const val AWS_SECRET_KEY = "aws_secret_key";
        const val AWS_S3_CDN_BUCKET = "aws_s3_cdn_bucket";


        fun getDefaultMock(): ApplicationPropertiesService
        {
            val mock = MockCreator.getApplicationPropertiesServiceMock();

            Mockito.`when`(mock.getAwsAccessKey()).thenReturn(AWS_ACCESS_KEY)
            Mockito.`when`(mock.getAwsSecretKey()).thenReturn(AWS_SECRET_KEY)
            Mockito.`when`(mock.getAwsS3CdnBucket()).thenReturn(AWS_S3_CDN_BUCKET)

            return mock
        }
    }
}