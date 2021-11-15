package com.kbalazsworks.ses.services

import com.kbalazsworks.ses.requests.PostCompanyOwnEmailRequest
import com.kbalazsworks.ses.value_object.CompanyOwnEmail
import com.kbalazsworks.ses.value_object.CompanyOwnRawEmail
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RequestMapperService {
    fun map(request: PostCompanyOwnEmailRequest) = CompanyOwnRawEmail(
        request.to,
        hashMapOf("name" to request.varName, "ownUrl" to request.varOwnUrl)
    )
}
