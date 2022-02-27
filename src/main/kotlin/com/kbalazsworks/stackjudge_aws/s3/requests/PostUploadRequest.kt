package com.kbalazsworks.stackjudge_aws.s3.requests

import org.jboss.resteasy.reactive.PartType
import org.jboss.resteasy.reactive.RestForm
import org.jboss.resteasy.reactive.multipart.FileUpload
import javax.ws.rs.core.MediaType

class PostUploadRequest {
    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var cdnNamespace: String

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var subFolder: String

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var fileName: String

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    lateinit var fileExtension: String

    @RestForm("content")
    lateinit var content: FileUpload
}
