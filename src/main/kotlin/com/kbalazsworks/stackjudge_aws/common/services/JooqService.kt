package com.kbalazsworks.stackjudge_aws.common.services

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class JooqService(val connectionService: ConnectionService) {
    private var dslContext: DSLContext? = null
    fun getQueryBuilder(): DSLContext {
        if (null == dslContext) {
            dslContext = DSL.using(connectionService.connection, SQLDialect.POSTGRES)
        }

        return dslContext as DSLContext
    }
}