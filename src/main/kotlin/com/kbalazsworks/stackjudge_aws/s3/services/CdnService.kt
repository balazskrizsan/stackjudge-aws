package com.kbalazsworks.stackjudge_aws.s3.services

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService
import com.kbalazsworks.stackjudge_aws.common.services.DateTimeFormatterService
import com.kbalazsworks.stackjudge_aws.s3.exception.S3PutException
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CdnService(
    private val applicationPropertiesService: ApplicationPropertiesService,
    private val dateTimeFormatterService: DateTimeFormatterService,
    private val s3Repository: S3Repository,
    private val localDateTimeFactory: LocalDateTimeFactory
) {
    val logger = LoggerFactory.getLogger(CdnService::class.toString())

    @Throws(AmazonS3Exception::class)
    fun put(put: Put): CdnServicePutResponse {
        var pathAndFile = ""

        return try {
            val content = put.content
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentLength = content.length.toLong()
            val unixTimestamp = dateTimeFormatterService.toEpoch(localDateTimeFactory.create())
            val fullFileName = "/" + put.fileName + "-" + unixTimestamp + "." + put.fileExtension
            pathAndFile = put.cdnNamespaceEnum.value + put.subFolder + fullFileName

            val s3Response = s3Repository.put(
                PutObjectRequest(
                    applicationPropertiesService.awsS3CdnBucket,
                    pathAndFile,
                    ByteArrayInputStream(content.toByteArray()),
                    objectMetadata
                )
            )

            logger.info("S3 upload: {} {}", pathAndFile, put);

            CdnServicePutResponse(
                pathAndFile,
                fullFileName,
                s3Response.eTag,
                s3Response.contentMd5
            )
        } catch (e: Exception) {
            logger.info("AWS S3 upload error on: $pathAndFile")

            throw S3PutException("AWS S3 upload error on: $pathAndFile")
        }
    }
}
