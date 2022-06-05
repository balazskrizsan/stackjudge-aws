package com.kbalazsworks.stackjudge_aws.ses.value_object

import net.afanasev.sekret.Secret

data class CompanyOwnRawEmail(@Secret val to: String, val contex: HashMap<String, String>)
