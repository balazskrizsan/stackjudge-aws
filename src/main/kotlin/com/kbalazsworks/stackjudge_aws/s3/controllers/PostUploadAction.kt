package com.kbalazsworks.stackjudge_aws.s3.controllers

import com.kbalazsworks.oidc.services.IOidcService
import com.kbalazsworks.stackjudge_aws.common.builders.ResponseEntityBuilder
import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData
import com.kbalazsworks.stackjudge_aws.s3.requests.PostUploadRequest
import com.kbalazsworks.stackjudge_aws.s3.services.CdnService
import com.kbalazsworks.stackjudge_aws.s3.services.RequestMapperService
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse
import com.kbalazsworks.stackjudge_aws.ses.controllers.PostSendSendCompanyOwnEmailAction
import org.jboss.resteasy.reactive.MultipartForm
import org.jboss.resteasy.reactive.RestHeader
import org.slf4j.LoggerFactory
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/s3/upload")
class PostUploadAction(
    private val cdnService: CdnService,
    private val requestMapperService: RequestMapperService,
    private val oidcService: IOidcService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(PostSendSendCompanyOwnEmailAction::class.toString())
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun postSendAction(
        @MultipartForm request: PostUploadRequest,
        @RestHeader("Authorization") token: String,
    ): ApiResponseData<CdnServicePutResponse> {
        oidcService.checkScopesInToken(token, listOf("sj.aws.ec2.upload_company_logo", "sj.aws.ec2.upload_company_map"))

        val mappedRequest = requestMapperService.map(request)

        logger.info("API call: {}", mappedRequest)

        return ResponseEntityBuilder<CdnServicePutResponse>()
            .data(cdnService.put(mappedRequest))
            .build()
    }
}
