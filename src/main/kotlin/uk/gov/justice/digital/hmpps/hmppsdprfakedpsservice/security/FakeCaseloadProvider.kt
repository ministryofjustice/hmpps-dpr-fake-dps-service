package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.security

import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.CaseloadProvider

@Component
class FakeCaseloadProvider : CaseloadProvider {
  override fun getActiveCaseloadIds(jwt: Jwt): List<String> {
    return listOf("FAKE")
  }
}
