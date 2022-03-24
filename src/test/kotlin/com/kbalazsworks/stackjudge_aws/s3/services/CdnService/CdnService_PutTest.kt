package com.kbalazsworks.stackjudge_aws.s3.services.CdnService

import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import com.kbalazsworks.stackjudge_aws.s3.enums.CdnNamespaceEnum
import com.kbalazsworks.stackjudge_aws.s3.exception.S3PutException
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put
import com.kbalazsworks.stackjudge_aws.test_helpers.ApplicationPropertiesServiceMocker
import com.kbalazsworks.stackjudge_aws.test_helpers.LocalDateTimeFactoryMocker
import com.kbalazsworks.stackjudge_aws.test_helpers.S3RepositoryMocker
import com.kbalazsworks.stackjudge_aws.test_helpers.S3ServiceProvider
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import javax.inject.Inject

@QuarkusTest
class CdnService_PutTest {
    @Inject
    lateinit var s3ServiceProvider: S3ServiceProvider

    @Test
    fun callsWithValidData_returnsCdnInformation() {
        // Arrange
        val testedPurRequest =
            Put(CdnNamespaceEnum.COMPANY_LOGOS, "string", "file.name", "ext", "content")

        val content = "content"
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = content.length.toLong()
        val expectedPutCall = PutObjectRequest(
            "aws_s3_cdn_bucket",
            "company-logosstring/file.name-1577934245.ext",
            ByteArrayInputStream(content.toByteArray()),
            objectMetadata
        )

        val mockedPutResponse = PutObjectResult()
        mockedPutResponse.eTag = "e_tag_mock"
        mockedPutResponse.contentMd5 = "content_md5_mock"

        val expectedResponse = CdnServicePutResponse(
            "company-logosstring/file.name-1577934245.ext",
            "/file.name-1577934245.ext",
            "e_tag_mock",
            "content_md5_mock"
        )

        // Act
        val actual = s3ServiceProvider
            .getCdnService(
                ApplicationPropertiesServiceMocker.getDefaultMock(),
                null,
                S3RepositoryMocker.put_returns_PutObjectResult(expectedPutCall, mockedPutResponse),
                LocalDateTimeFactoryMocker.getDefault()
            )
            .put(testedPurRequest)

        // Assert
        assertThat(actual).isEqualTo(expectedResponse)
    }

    @Test
    fun putWithInvalidData_throwsException() {
        // Arrange
        val testedPurRequest =
            Put(CdnNamespaceEnum.COMPANY_LOGOS, "string", "file.name", "ext", "content")

        // Act - Assert
        val service = s3ServiceProvider
            .getCdnService(
                ApplicationPropertiesServiceMocker.getDefaultMock(),
                null,
                S3RepositoryMocker.put_returns_exception(),
                LocalDateTimeFactoryMocker.getDefault()
            )

        assertThatThrownBy {
            service.put(testedPurRequest)
        }
            .isExactlyInstanceOf(S3PutException::class.java)
            .hasMessage("AWS S3 upload error on: company-logosstring/file.name-1577934245.ext")
    }
}
