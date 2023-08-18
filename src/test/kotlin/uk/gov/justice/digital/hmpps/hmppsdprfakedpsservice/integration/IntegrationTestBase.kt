package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
abstract class IntegrationTestBase {
  @Value("\${security.api-token}")
  lateinit var apiToken: String

  @Autowired
  lateinit var webTestClient: WebTestClient
}
