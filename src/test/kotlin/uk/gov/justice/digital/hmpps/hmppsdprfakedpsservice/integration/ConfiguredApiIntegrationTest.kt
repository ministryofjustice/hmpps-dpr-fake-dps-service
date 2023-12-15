package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.data.FakePreferencesRepository
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model.FakePreferences
import java.time.LocalDateTime

class ConfiguredApiIntegrationTest : IntegrationTestBase() {

  @Autowired
  lateinit var fakePreferencesRepository: FakePreferencesRepository

  @BeforeEach
  fun clearData() {
    fakePreferencesRepository.deleteAll()
  }

  @Test
  fun `Get list returns empty array successfully`() {
    webTestClient.get()
      .uri("/reports/fake-preferences/all")
      .header("Authorization", "Bearer x")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .json("[]")
  }

  @Test
  fun `Get list returns data correctly`() {
    val expected = FakePreferences("1", "Cat", "Green", LocalDateTime.parse("2001-02-03T01:02:02"))

    fakePreferencesRepository.save(expected)

    val result = webTestClient.get()
      .uri("/reports/fake-preferences/all")
      .header("Authorization", "Bearer x")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody(String::class.java)
      .returnResult()
      .responseBody!!

    assertThat(result).isEqualTo(
      """
        [{"last_updated":"2001-02-03T01:02:02","favourite_animal":"Cat","favourite_colour":"Green","prisoner_number":"1"}]
      """.trimIndent(),
    )
  }
}
