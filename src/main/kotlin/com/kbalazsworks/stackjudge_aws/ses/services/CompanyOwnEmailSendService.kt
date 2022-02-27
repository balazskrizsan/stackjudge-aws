package com.kbalazsworks.stackjudge_aws.ses.services

import com.kbalazsworks.stackjudge_aws.common.services.TemplateService
import com.kbalazsworks.stackjudge_aws.ses.expections.SesSendException
import com.kbalazsworks.stackjudge_aws.ses.value_object.CompanyOwnEmail
import com.kbalazsworks.stackjudge_aws.ses.value_object.CompanyOwnRawEmail
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