package com.kbalazsworks.s3.controllers

import com.kbalazsworks.common.builders.ResponseEntityBuilder
import com.kbalazsworks.s3.requests.PostUploadRequest
import com.kbalazsworks.s3.services.CdnService
import com.kbalazsworks.s3.services.RequestMapperService
import com.kbalazsworks.stackjudge.domain.aws_module.value_objects.CdnServicePutResponse
import org.jboss.resteasy.reactive.MultipartForm
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/s3/upload")
class PostUploadAction(private val cdnService: CdnService, private val requestMapperService: RequestMapperService) {
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun postSendAction(@MultipartForm request: PostUploadRequest) =
        ResponseEntityBuilder<CdnServicePutResponse>()
            .data(cdnService.put(requestMapperService.map(request)))
            .build()
}
