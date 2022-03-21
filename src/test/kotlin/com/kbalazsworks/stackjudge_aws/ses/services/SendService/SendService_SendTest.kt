package com.kbalazsworks.stackjudge_aws.ses.services.SendService

import com.amazonaws.services.simpleemail.model.*
import com.kbalazsworks.stackjudge_aws.ses.value_object.CompanyOwnEmail
import com.kbalazsworks.stackjudge_aws.test_helpers.AmazonSimpleEmailServiceFactoryMocker
import com.kbalazsworks.stackjudge_aws.test_helpers.MockCreator
import com.kbalazsworks.stackjudge_aws.test_helpers.SesServiceProvider
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import javax.inject.Inject

@QuarkusTest
class SendService_SendTest {
    @Inject
    lateinit var sesServiceProvider: SesServiceProvider

    @Test
    fun validParameters_callsSesSendService() {
        // Arrange
        val testedCompanyOwnEmail = CompanyOwnEmail("to@email.com", "subject", "html", "text")

        val expectedSendRequest = SendEmailRequest()
            .withDestination(Destination().withToAddresses("to@email.com"))
            .withMessage(
                Message()
                    .withBody(
                        Body()
                            .withHtml(Content().withCharset("UTF-8").withData("html"))
                            .withText(Content().withCharset("UTF-8").withData("text"))
                    )
                    .withSubject(Content().withCharset("UTF-8").withData("subject"))
            )
            .withSource("krizsan.balazs@gmail.com")

        val amazonSimpleEmailServiceMock = MockCreator.getAmazonSimpleEmailServiceMock()

        // Act
        sesServiceProvider
            .getSendService(AmazonSimpleEmailServiceFactoryMocker.create_returns_mock(amazonSimpleEmailServiceMock))
            .send(testedCompanyOwnEmail)

        // Assert
        verify(amazonSimpleEmailServiceMock).sendEmail(expectedSendRequest)
    }
}
