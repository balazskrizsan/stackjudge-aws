package com.kbalazsworks.stackjudge_aws.test_helpers

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService
import com.kbalazsworks.stackjudge_aws.common.services.DateTimeFormatterService
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository
import com.kbalazsworks.stackjudge_aws.ses.factories.AmazonSimpleEmailServiceFactory
import org.mockito.Mockito.mock

class MockCreator {
    companion object {
        fun getAmazonSimpleEmailServiceMock() = mock(AmazonSimpleEmailService::class.java)
        fun getAmazonSimpleEmailServiceFactoryMock() = mock(AmazonSimpleEmailServiceFactory::class.java)
        fun getS3RepositoryMock() = mock(S3Repository::class.java)
        fun getApplicationPropertiesServiceMock() = mock(ApplicationPropertiesService::class.java)
        fun getLocalDateTimeFactoryMock() = mock(LocalDateTimeFactory::class.java)
    }
}
