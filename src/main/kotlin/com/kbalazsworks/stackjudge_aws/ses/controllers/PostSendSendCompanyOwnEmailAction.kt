package com.kbalazsworks.stackjudge_aws.ses.controllers

import com.kbalazsworks.stackjudge_aws.common.builders.ResponseEntityBuilder
import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse
import com.kbalazsworks.stackjudge_aws.ses.requests.PostCompanyOwnEmailRequest
import com.kbalazsworks.stackjudge_aws.ses.services.CompanyOwnEmailSendService
import com.kbalazsworks.stackjudge_aws.ses.services.RequestMapperService
import com.kbalazsworks.stackjudge_aws.ses.services.SendService
import org.jboss.resteasy.reactive.MultipartForm
import org.slf4j.LoggerFactory
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/ses/send/company-own-email")
class PostSendSendCompanyOwnEmailAction(
    private val companyOwnEmailSendService: CompanyOwnEmailSendService,
    private val requestMapperService: RequestMapperService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(PostSendSendCompanyOwnEmailAction::class.toString())
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun postSendAction(@MultipartForm request: PostCompanyOwnEmailRequest): ApiResponseData<String> {
        val mappedRequest = requestMapperService.map(request)

        logger.info("API call: {}", mappedRequest)

        companyOwnEmailSendService.send(mappedRequest)

        return ResponseEntityBuilder<String>().build()
    }
}
