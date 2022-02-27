package com.kbalazsworks.stackjudge_aws.ses.controllers

import com.kbalazsworks.stackjudge_aws.ses.requests.PostCompanyOwnEmailRequest
import com.kbalazsworks.stackjudge_aws.ses.services.CompanyOwnEmailSendService
import com.kbalazsworks.stackjudge_aws.ses.services.RequestMapperService
import org.jboss.resteasy.reactive.MultipartForm
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
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    fun postSendAction(@MultipartForm request: PostCompanyOwnEmailRequest) =
        companyOwnEmailSendService.send(requestMapperService.map(request))
}
