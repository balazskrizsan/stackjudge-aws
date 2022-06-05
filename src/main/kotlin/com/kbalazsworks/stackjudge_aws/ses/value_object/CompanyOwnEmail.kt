package com.kbalazsworks.stackjudge_aws.ses.value_object

import net.afanasev.sekret.Secret

data class CompanyOwnEmail(@Secret val to: String, val subject: String, val html: String, val text: String)
