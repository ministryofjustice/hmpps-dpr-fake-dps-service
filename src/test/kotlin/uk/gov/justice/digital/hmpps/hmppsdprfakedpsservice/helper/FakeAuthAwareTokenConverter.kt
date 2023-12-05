package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.helper

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.AuthAwareAuthenticationToken
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.AuthAwareTokenConverter
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.security.FakeCaseloadProvider

@Component
class FakeAuthAwareTokenConverter(
  val fakeCaseloadProvider: FakeCaseloadProvider
) : AuthAwareTokenConverter {

  override fun convert(jwt: Jwt): AuthAwareAuthenticationToken {
    val principal = "FAKE"
    val authorities = listOf(SimpleGrantedAuthority("ROLE_PRISONS_REPORTING_USER"))

    return AuthAwareAuthenticationToken(jwt, principal, authorities, fakeCaseloadProvider)
  }
}
