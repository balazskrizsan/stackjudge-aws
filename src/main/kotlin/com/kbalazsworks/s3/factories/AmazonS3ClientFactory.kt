package com.kbalazsworks.s3.factories

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.kbalazsworks.common.services.ApplicationPropertiesService
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class AmazonS3ClientFactory(private val applicationPropertiesService: ApplicationPropertiesService) {
    fun create() = AmazonS3ClientBuilder
        .standard()
        .withRegion(Regions.EU_CENTRAL_1)
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(
                    applicationPropertiesService.getAwsAccessKey(),
                    applicationPropertiesService.getAwsSecretKey()
                )
            )
        )
        .build()
}
