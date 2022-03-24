package com.kbalazsworks.stackjudge_aws.s3.exception

import com.kbalazsworks.stackjudge_aws.common.exceptions.HttpException

class S3PutException(message: String) : HttpException(message)
