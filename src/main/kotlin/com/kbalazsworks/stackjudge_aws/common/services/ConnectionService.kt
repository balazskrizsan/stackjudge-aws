package com.kbalazsworks.stackjudge_aws.common.services

import java.sql.Connection
import java.sql.SQLException
import javax.enterprise.context.ApplicationScoped
import javax.sql.DataSource

@ApplicationScoped
class ConnectionService (private val dataSource: DataSource) {
    @get:Throws(SQLException::class)
    var connection: Connection? = null
        get() {
            if (null == field) {
                field = dataSource.connection
            }
            return field
        }
}