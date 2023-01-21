package com.kbalazsworks.stackjudge_aws.test_helpers

import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService
import com.kbalazsworks.stackjudge_aws.common.services.DateTimeFormatterService
import com.kbalazsworks.stackjudge_aws.s3.repositories.RemoteFileRepository
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository
import com.kbalazsworks.stackjudge_aws.s3.services.CdnService
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class S3ServiceProvider(
    private val applicationPropertiesService: ApplicationPropertiesService,
    private val dateTimeFormatterService: DateTimeFormatterService,
    private val s3Repository: S3Repository,
    private val remoteFileRepository: RemoteFileRepository,
    private val localDateTimeFactory: LocalDateTimeFactory
) {
    fun getCdnService() = getCdnService(
        applicationPropertiesService,
        dateTimeFormatterService,
        s3Repository,
        localDateTimeFactory
    )

    fun getCdnService(
        applicationPropertiesServiceReplacer: ApplicationPropertiesService?,
        dateTimeFormatterServiceReplacer: DateTimeFormatterService?,
        s3RepositoryReplacer: S3Repository?,
        localDateTimeFactoryReplacer: LocalDateTimeFactory?
    ) = CdnService(
        applicationPropertiesServiceReplacer ?: applicationPropertiesService,
        dateTimeFormatterServiceReplacer ?: dateTimeFormatterService,
        s3RepositoryReplacer ?: s3Repository,
        remoteFileRepository,
        localDateTimeFactoryReplacer ?: localDateTimeFactory,
    )
}
