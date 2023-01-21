package com.kbalazsworks.stackjudge_aws.s3.repositories

import com.kbalazsworks.stackjudge_aws.common.services.JooqService
import com.kbalazsworks.stackjudge_aws.s3.entities.RemoteFile
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RemoteFileRepository(private val jooqService: JooqService) {
    private var remoteFilesTable: com.kbalazsworks.stackjudge_aws.db.tables.RemoteFiles =
        com.kbalazsworks.stackjudge_aws.db.tables.RemoteFiles.REMOTE_FILES

    fun save(remoteFile: RemoteFile): RemoteFile {
        val qB = jooqService.getQueryBuilder()
        val record: RemoteFile? = qB.insertInto(
            remoteFilesTable,
            remoteFilesTable.PATH,
            remoteFilesTable.FILENAME,
            remoteFilesTable.S3_E_TAG,
            remoteFilesTable.S3_CONTENT_MD5,
        )
            .values(
                remoteFile.path,
                remoteFile.filename,
                remoteFile.s3ETag,
                remoteFile.s3ContentMd5,
            )
            .returningResult()
            .fetchOneInto(RemoteFile::class.java)

        if (record != null) {
            return record
        }

        throw Exception("todo")
    }
}