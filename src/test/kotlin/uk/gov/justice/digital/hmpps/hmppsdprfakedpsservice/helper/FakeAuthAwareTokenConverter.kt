package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.helper

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.AuthAwareAuthenticationToken
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.AuthAwareTokenConverter

@Component
class FakeAuthAwareTokenConverter : AuthAwareTokenConverter {
  override fun convert(jwt: Jwt): AuthAwareAuthenticationToken {
    val principal = "FAKE"
    val authorities = listOf(SimpleGrantedAuthority("ROLE_PRISONS_REPORTING_USER"))

    return AuthAwareAuthenticationToken(jwt, principal, authorities, listOf("FAKE"))
  }
}
