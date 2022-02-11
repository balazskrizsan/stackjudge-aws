package com.kbalazsworks.s3.controllers

import com.kbalazsworks.s3.requests.PostUploadRequest
import org.jboss.resteasy.reactive.MultipartForm
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/s3/upload")
class PostUpload() {
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    fun postSendAction(@MultipartForm request: PostUploadRequest) {
        System.out.println("cdnNamespace: " + request.cdnNamespace)
        System.out.println("filename: " + request.fileName)
        System.out.println("extension: " + request.fileExtension)
        System.out.println("content size: " + request.content.size())
        System.out.println(request.content)
    }
}
