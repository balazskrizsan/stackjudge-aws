package com.kbalazsworks.common.services

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ApplicationPropertiesService {
    fun getAwsS3CdnBucket() = ""
    fun getAwsAccessKey() = ""
    fun getAwsSecretKey() = ""
}
