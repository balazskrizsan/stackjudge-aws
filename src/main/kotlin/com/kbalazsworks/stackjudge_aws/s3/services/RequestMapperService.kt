package com.kbalazsworks.stackjudge_aws.s3.services

import com.kbalazsworks.stackjudge_aws.s3.enums.CdnNamespaceEnum
import com.kbalazsworks.stackjudge_aws.s3.requests.PostUploadRequest
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put
import javax.enterprise.context.ApplicationScoped
import kotlin.io.path.readBytes

@ApplicationScoped
class RequestMapperService {
    fun map(request: PostUploadRequest) = Put(
        CdnNamespaceEnum.valueOf(request.cdnNamespace),
        request.subFolder,
        request.fileName,
        request.fileExtension,
        request.content.uploadedFile().readBytes().toString()
    )
}
