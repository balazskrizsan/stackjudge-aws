package com.kbalazsworks.stackjudge_aws.ses.requests

import org.jboss.resteasy.reactive.PartType
import org.jboss.resteasy.reactive.RestForm
import javax.ws.rs.core.MediaType

class PostCompanyOwnEmailRequest {
    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var to: String

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var varName: String

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var varOwnUrl: String
}
