package com.kbalazsworks.s3.services

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.kbalazsworks.common.factories.LocalDateTimeFactory
import com.kbalazsworks.common.services.ApplicationPropertiesService
import com.kbalazsworks.common.services.DateTimeFormatterService
import com.kbalazsworks.s3.repositories.S3Repository
import com.kbalazsworks.s3.value_objects.Put
import com.kbalazsworks.stackjudge.domain.aws_module.value_objects.CdnServicePutResponse
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

            val response = CdnServicePutResponse(
                s3Repository.put(
                    PutObjectRequest(
                        applicationPropertiesService.getAwsS3CdnBucket(),
                        pathAndFile,
                        content.inputStream(),
                        objectMetadata
                    )
                ),
                pathAndFile,
                fullFileName
            )
//            log.info("Successful AWS S3 upload: " + response.path())
            response
        } catch (e: IOException) {
            throw AmazonS3Exception("AWS S3 upload error on: $pathAndFile")
        }
    }
}
