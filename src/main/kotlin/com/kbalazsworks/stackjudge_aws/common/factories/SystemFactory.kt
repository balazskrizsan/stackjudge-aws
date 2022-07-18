package com.kbalazsworks.stackjudge_aws.common.factories

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SystemFactory {
    fun getCurrentTimeMillis() = System.currentTimeMillis()
}
