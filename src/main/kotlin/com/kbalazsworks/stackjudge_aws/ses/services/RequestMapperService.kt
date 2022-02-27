package com.kbalazsworks.stackjudge_aws.ses.services

import com.kbalazsworks.stackjudge_aws.ses.requests.PostCompanyOwnEmailRequest
import com.kbalazsworks.stackjudge_aws.ses.value_object.CompanyOwnRawEmail
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RequestMapperService {
    fun map(request: PostCompanyOwnEmailRequest) = CompanyOwnRawEmail(
        request.to,
        hashMapOf("name" to request.varName, "ownUrl" to request.varOwnUrl)
    )
}
