package com.kbalazsworks.stackjudge_aws.test_helpers

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.kbalazsworks.stackjudge_aws.ses.factories.AmazonSimpleEmailServiceFactory
import org.mockito.Mockito.mock

class MockCreator {
    companion object {
        fun getAmazonSimpleEmailServiceMock(): AmazonSimpleEmailService {
            return mock(AmazonSimpleEmailService::class.java)
        }

        fun getAmazonSimpleEmailServiceFactoryMock(): AmazonSimpleEmailServiceFactory {
            return mock(AmazonSimpleEmailServiceFactory::class.java)
        }
    }
}
