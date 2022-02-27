package com.kbalazsworks.stackjudge_aws.common.services

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ApplicationPropertiesService {

    @ConfigProperty(name = "aws.access_key")
    var awsAccessKeyVal: String = ""
    fun getAwsAccessKey() = awsAccessKeyVal

    @ConfigProperty(name = "aws.secret_key")
    var awsSecretKeyVal: String = ""
    fun getAwsSecretKey() = awsSecretKeyVal

    @ConfigProperty(name = "aws.s3.cdn_bucket")
    var awsS3CdnBucketVal: String = ""
    fun getAwsS3CdnBucket() = awsS3CdnBucketVal
}
