package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.config

import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.Parameter
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.method.HandlerMethod

const val AUTH_TOKEN_HEADER_NAME: String = "x-api-token"
const val TEST_USER_ROLE: String = "TEST_USER"

@Configuration
class SecurityConfig(
  val authenticationFilter: AuthenticationFilter,
) {
  @Bean
  @Throws(Exception::class)
  fun filterChain(http: HttpSecurity): SecurityFilterChain? {
    http {
      headers { frameOptions { sameOrigin = true } }
      sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
      // Can't have CSRF protection as requires session
      csrf { disable() }
      authorizeHttpRequests {
        listOf(
          "/health/**",
          "/info",
          "/error",
          "/v3/api-docs",
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/swagger-ui.html",
          "/swagger-resources",
          "/swagger-resources/configuration/ui",
          "/swagger-resources/configuration/security",
        ).forEach { authorize(it, permitAll) }
        authorize(anyRequest, hasAuthority(TEST_USER_ROLE))
      }
      addFilterBefore<UsernamePasswordAuthenticationFilter>(authenticationFilter)
    }
    return http.build()
  }

  @Bean
  fun documentApiTokenHeader(): OperationCustomizer? {
    return OperationCustomizer { operation: Operation, handlerMethod: HandlerMethod? ->
      operation.addParametersItem(
        Parameter()
          .`in`("header")
          .required(true)
          .description("API Token (see README for details)")
          .name(AUTH_TOKEN_HEADER_NAME),
      )
    }
  }
}
