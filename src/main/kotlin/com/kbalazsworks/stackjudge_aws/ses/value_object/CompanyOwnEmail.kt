package com.kbalazsworks.stackjudge_aws.ses.value_object

data class CompanyOwnEmail(val to: String, val subject: String, val html: String, val text: String)