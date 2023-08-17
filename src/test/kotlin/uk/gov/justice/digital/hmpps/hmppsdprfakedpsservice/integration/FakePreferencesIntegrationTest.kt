package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.integration

import org.junit.jupiter.api.Test

class FakePreferencesIntegrationTest : IntegrationTestBase() {

  @Test
  fun `Get returns empty array`() {
    webTestClient.get()
      .uri("/fake-preferences")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .json("[]")
  }
}
