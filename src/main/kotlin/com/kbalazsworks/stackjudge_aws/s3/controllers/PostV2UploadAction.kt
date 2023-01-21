package com.kbalazsworks.stackjudge_aws.s3.controllers

import com.kbalazsworks.stackjudge_aws.common.builders.ResponseEntityBuilder
import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData
import com.kbalazsworks.stackjudge_aws.oidc.OidcServiceFactory
import com.kbalazsworks.stackjudge_aws.s3.requests.PostUploadRequest
import com.kbalazsworks.stackjudge_aws.s3.responses.PutAndSaveResponse
import com.kbalazsworks.stackjudge_aws.s3.services.CdnService
import com.kbalazsworks.stackjudge_aws.s3.services.RequestMapperService
import org.jboss.resteasy.reactive.MultipartForm
import org.jboss.resteasy.reactive.RestHeader
import org.slf4j.LoggerFactory
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v2/s3/upload")
class PostV2UploadAction(
    private val cdnService: CdnService,
    private val requestMapperService: RequestMapperService,
    private val oidcServiceFactory: OidcServiceFactory
) {
    companion object {
        private val logger = LoggerFactory.getLogger(PostV2UploadAction::class.toString())
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun postSendAction(
        @MultipartForm request: PostUploadRequest,
        @RestHeader("Authorization") token: String,
    ): ApiResponseData<PutAndSaveResponse> {
        oidcServiceFactory.get()
            .checkScopesInToken(token, listOf("sj.aws.ec2.upload_company_logo", "sj.aws.ec2.upload_company_map"))

        val mappedRequest = requestMapperService.map(request)

        logger.info("API call: {}", mappedRequest)

        return ResponseEntityBuilder<PutAndSaveResponse>()
            .data(cdnService.putAndSave(mappedRequest))
            .build()
    }
}
