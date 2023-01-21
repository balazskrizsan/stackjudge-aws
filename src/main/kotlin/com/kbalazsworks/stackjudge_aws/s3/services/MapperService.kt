package com.kbalazsworks.stackjudge_aws.s3.services

import com.kbalazsworks.stackjudge_aws.s3.entities.RemoteFile
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse

class MapperService {
    companion object {
        fun mapCdnServicePutResponseToRemoteFile(cdnServicePutResponse: CdnServicePutResponse): RemoteFile {
            return RemoteFile(
                null,
                cdnServicePutResponse.path,
                cdnServicePutResponse.fileName,
                cdnServicePutResponse.s3eTag,
                cdnServicePutResponse.s3contentMd5
            )
        }
    }
}