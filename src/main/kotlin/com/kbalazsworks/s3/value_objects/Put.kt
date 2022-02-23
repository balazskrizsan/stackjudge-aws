package com.kbalazsworks.s3.value_objects

import com.kbalazsworks.s3.enums.CdnNamespaceEnum
import org.jboss.resteasy.reactive.multipart.FileUpload

data class Put(
    var cdnNamespaceEnum: CdnNamespaceEnum,
    var subFolder: String,
    var fileName: String,
    var fileExtension: String,
    var content: FileUpload
)
