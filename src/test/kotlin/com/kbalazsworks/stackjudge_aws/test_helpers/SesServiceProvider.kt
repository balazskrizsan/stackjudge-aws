package com.kbalazsworks.stackjudge_aws.test_helpers

import com.kbalazsworks.stackjudge_aws.ses.factories.AmazonSimpleEmailServiceFactory
import com.kbalazsworks.stackjudge_aws.ses.services.SendService
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SesServiceProvider(private val amazonSimpleEmailServiceFactory: AmazonSimpleEmailServiceFactory) {

    fun getSendService() = getSendService(amazonSimpleEmailServiceFactory)
    fun getSendService(amazonSimpleEmailServiceFactoryReplacer: AmazonSimpleEmailServiceFactory?) =
        SendService(amazonSimpleEmailServiceFactoryReplacer ?: amazonSimpleEmailServiceFactory)
}
