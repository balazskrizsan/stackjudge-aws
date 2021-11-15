package com.kbalazsworks.ses.factories

import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class AmazonSimpleEmailServiceFactory {
    fun create(): AmazonSimpleEmailService {
        return AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build()
    }
}