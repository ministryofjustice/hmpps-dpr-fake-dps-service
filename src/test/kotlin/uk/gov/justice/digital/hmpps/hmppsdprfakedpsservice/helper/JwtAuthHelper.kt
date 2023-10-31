package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.helper

import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Collections.singletonMap

@Component
class JwtAuthHelper {
  @Bean
  fun jwtDecoder(): JwtDecoder {
    return FakeJwtDecoder()
  }

  class FakeJwtDecoder : JwtDecoder {
    override fun decode(token: String?): Jwt {
      val fakeMap : Map<String, Any> = singletonMap("Fake", "Fake")
      return Jwt("Fake", Instant.now(), Instant.MAX, fakeMap, fakeMap)
    }

  }
}
