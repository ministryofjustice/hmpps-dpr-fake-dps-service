package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.util.Collections.singletonList

const val AUTH_TOKEN_HEADER_NAME: String = "x-api-token"
const val TEST_USER_ROLE: String = "TEST_USER"

@Component
class AuthenticationFilter(
  @Value("\${security.api-token}")
  val apiToken: String,
) : GenericFilterBean() {

  override fun doFilter(request: ServletRequest?, response: ServletResponse, filterChain: FilterChain) {
    val apiKey: String? = (request as HttpServletRequest).getHeader(AUTH_TOKEN_HEADER_NAME)

    if (apiKey == apiToken) {
      SecurityContextHolder.getContext().authentication = AnonymousAuthenticationToken(
        apiKey,
        "Test User",
        singletonList(SimpleGrantedAuthority(TEST_USER_ROLE)),
      )
    }

    filterChain.doFilter(request, response)
  }
}
