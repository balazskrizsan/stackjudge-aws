package com.kbalazsworks.s3.services

import com.kbalazsworks.s3.enums.CdnNamespaceEnum
import com.kbalazsworks.s3.requests.PostUploadRequest
import com.kbalazsworks.s3.value_objects.Put
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RequestMapperService {
    fun map(request: PostUploadRequest) = Put(
        CdnNamespaceEnum.valueOf(request.cdnNamespace),
        request.subFolder,
        request.fileName,
        request.fileExtension,
        request.content
    )
}
