package com.kbalazsworks.stackjudge_aws.s3.services

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService
import com.kbalazsworks.stackjudge_aws.common.services.DateTimeFormatterService
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put
import org.slf4j.LoggerFactory
import java.io.IOException
import javax.enterprise.context.ApplicationScoped
import kotlin.io.path.readBytes

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
            val content = put.content.uploadedFile().readBytes()
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentLength = content.size.toLong()
            val unixTimestamp: Long = dateTimeFormatterService.toEpoch(localDateTimeFactory.create())
            val fullFileName = "/" + put.fileName + "-" + unixTimestamp + "." + put.fileExtension
            pathAndFile = put.cdnNamespaceEnum.value + put.subFolder + fullFileName

            val s3Response = s3Repository.put(
                PutObjectRequest(
                    applicationPropertiesService.getAwsS3CdnBucket(),
                    pathAndFile,
                    content.inputStream(),
                    objectMetadata
                )
            );

            val response = CdnServicePutResponse(
                pathAndFile,
                fullFileName,
                s3Response.eTag,
                s3Response.contentMd5
            )

            logger.info("S3 upload: {} {}", response.path, put);

            response
        } catch (e: IOException) {
            logger.info("AWS S3 upload error on: $pathAndFile")

            throw AmazonS3Exception("AWS S3 upload error on: $pathAndFile")
        }
    }
}
