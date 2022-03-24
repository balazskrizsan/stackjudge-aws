package com.kbalazsworks.stackjudge_aws.test_helpers

import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

class LocalDateTimeFactoryMocker {
    companion object {
        fun getDefault(): LocalDateTimeFactory {
            val mock = MockCreator.getLocalDateTimeFactoryMock()

            val mockReturn = LocalDateTime.of(2020, 1, 2, 3, 4, 5) // unix: 1577934245
            `when`(mock.create()).thenReturn(mockReturn)

            return mock
        }
    }
}
