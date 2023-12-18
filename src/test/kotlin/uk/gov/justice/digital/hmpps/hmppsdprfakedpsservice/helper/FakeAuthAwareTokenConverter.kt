package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.helper

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.DprAuthAwareAuthenticationToken
import uk.gov.justice.digital.hmpps.digitalprisonreportinglib.security.DprAuthAwareTokenConverter
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.security.FakeCaseloadProvider

@Component
class FakeAuthAwareTokenConverter(
  val fakeCaseloadProvider: FakeCaseloadProvider,
) : DprAuthAwareTokenConverter {

  override fun convert(jwt: Jwt): DprAuthAwareAuthenticationToken {
    val principal = "FAKE"
    val authorities = listOf(SimpleGrantedAuthority("ROLE_PRISONS_REPORTING_USER"))

    return DprAuthAwareAuthenticationToken(jwt, principal, authorities, fakeCaseloadProvider)
  }
}
