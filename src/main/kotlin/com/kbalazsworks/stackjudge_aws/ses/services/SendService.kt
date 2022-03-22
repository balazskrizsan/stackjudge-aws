package com.kbalazsworks.stackjudge_aws.ses.services

import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import com.kbalazsworks.stackjudge_aws.ses.expections.SesSendException
import com.kbalazsworks.stackjudge_aws.ses.factories.AmazonSimpleEmailServiceFactory
import com.kbalazsworks.stackjudge_aws.ses.value_object.CompanyOwnEmail
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SendService(private val amazonSimpleEmailServiceFactory: AmazonSimpleEmailServiceFactory) {
    companion object {
        private const val EMAIL_SOURCE = "krizsan.balazs@gmail.com"
        private val logger = LoggerFactory.getLogger(SendService::class.toString())
    }

    @Throws(SesSendException::class)
    fun send(email: CompanyOwnEmail) {
        try {
            val client = amazonSimpleEmailServiceFactory.create()
            val request = SendEmailRequest()
                .withDestination(Destination().withToAddresses(email.to))
                .withMessage(
                    Message()
                        .withBody(
                            Body()
                                .withHtml(Content().withCharset("UTF-8").withData(email.html))
                                .withText(Content().withCharset("UTF-8").withData(email.text))
                        )
                        .withSubject(Content().withCharset("UTF-8").withData(email.subject))
                )
                .withSource(EMAIL_SOURCE)
            client.sendEmail(request)
        } catch (e: Exception) {
            logger.error("Email send error.", e)

            throw SesSendException("E-mail send error.")
        }
    }
}
