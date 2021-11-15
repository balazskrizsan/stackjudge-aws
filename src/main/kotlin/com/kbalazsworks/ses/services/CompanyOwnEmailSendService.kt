package com.kbalazsworks.ses.services

import com.kbalazsworks.common.services.TemplateService
import com.kbalazsworks.ses.expections.SesSendException
import com.kbalazsworks.ses.value_object.CompanyOwnEmail
import com.kbalazsworks.ses.value_object.CompanyOwnRawEmail
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CompanyOwnEmailSendService(private val sendService: SendService, private val templateService: TemplateService) {
    private val subject = "StackJudge - Company Own Request"

    @Throws(SesSendException::class)
    fun send(companyOwnRawEmail: CompanyOwnRawEmail) {
        val companyOwnHtml = templateService.render("mail/company_own.html", companyOwnRawEmail.contex)
        val companyOwnText = templateService.render("mail/company_own.txt", companyOwnRawEmail.contex)

        sendService.send(CompanyOwnEmail(companyOwnRawEmail.to, subject, companyOwnHtml, companyOwnText))
    }
}