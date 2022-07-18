package com.kbalazsworks.stackjudge_aws.common.services

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ApplicationPropertiesService {
    @field:ConfigProperty(name = "aws.access_key")
    lateinit var awsAccessKey: String

    @field:ConfigProperty(name = "aws.secret_key")
    lateinit var awsSecretKey: String

    @field:ConfigProperty(name = "aws.s3.cdn_bucket")
    lateinit var awsS3CdnBucket: String

    @field:ConfigProperty(name = "keystore.full_path")
    lateinit var keystoreFullPath: String
}
