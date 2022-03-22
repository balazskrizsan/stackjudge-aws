package com.kbalazsworks.stackjudge_aws.test_helpers

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.kbalazsworks.stackjudge_aws.ses.factories.AmazonSimpleEmailServiceFactory
import org.mockito.Mockito

class AmazonSimpleEmailServiceFactoryMocker {
    companion object {
        fun create_returns_mock(thanMock: AmazonSimpleEmailService?): AmazonSimpleEmailServiceFactory {
            val mock = MockCreator.getAmazonSimpleEmailServiceFactoryMock()

            Mockito.`when`(mock.create()).thenReturn(thanMock)

            return mock
        }
    }
}
