package com.kbalazsworks.stackjudge_aws.s3.responses

import com.kbalazsworks.stackjudge_aws.s3.entities.RemoteFile

data class PutAndSaveResponse(val remoteFile: RemoteFile)