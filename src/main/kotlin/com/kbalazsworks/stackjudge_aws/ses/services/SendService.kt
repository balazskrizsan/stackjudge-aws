package com.kbalazsworks.stackjudge_aws.ses.services;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.*
import com.kbalazsworks.stackjudge_aws.ses.expections.SesSendException
import com.kbalazsworks.stackjudge_aws.ses.factories.AmazonSimpleEmailServiceFactory
import com.kbalazsworks.stackjudge_aws.ses.value_object.CompanyOwnEmail
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SendService(private val amazonSimpleEmailServiceFactory: AmazonSimpleEmailServiceFactory) {
    private val FROM_EMAIL = "krizsan.balazs@gmail.com"

    @Throws(SesSendException::class)
    fun send(email: CompanyOwnEmail) {
        try {
            val client: AmazonSimpleEmailService = amazonSimpleEmailServiceFactory.create()
            val request: SendEmailRequest = SendEmailRequest()
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
                .withSource(FROM_EMAIL)
            client.sendEmail(request)
        } catch (e: Exception) {
//            log.error("Email send error.", e)
            throw SesSendException("E-mail send error.")
        }
    }
}
