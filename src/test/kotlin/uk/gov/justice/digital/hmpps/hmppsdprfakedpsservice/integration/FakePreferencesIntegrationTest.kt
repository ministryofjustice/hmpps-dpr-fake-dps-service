package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.returnResult
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.data.FakePreferencesRepository
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model.FakePreferences
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FakePreferencesIntegrationTest : IntegrationTestBase() {

  @Autowired
  lateinit var fakePreferencesRepository: FakePreferencesRepository

  @BeforeEach
  fun clearData() {
    fakePreferencesRepository.deleteAll()
  }

  @Test
  fun `Get returns empty array successfully`() {
    webTestClient.get()
      .uri("/fake-preferences")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .json("[]")
  }

  @Test
  fun `Get returns data correctly`() {
    val expected = FakePreferences("1", "Cat", "Green", LocalDateTime.now())
    val expectedDateString = DateTimeFormatter.ISO_DATE_TIME.format(expected.lastUpdated)

    fakePreferencesRepository.save(expected)

    val result = webTestClient.get()
      .uri("/fake-preferences")
      .exchange()
      .expectStatus()
      .isOk
      .returnResult<FakePreferences>()

    val resultBody = result.responseBody.blockLast()
    assertThat(resultBody!!.prisonerNumber).isEqualTo("1")
    assertThat("[0].favouriteAnimal").isEqualTo("Cat")
    assertThat("[0].favouriteColour").isEqualTo("Green")
    // Some implementations return a greater millisecond precision.
    assertThat("[0].lastUpdated").startsWith(expectedDateString)
  }

  @Test
  fun `Put saves data correctly`() {
    val body = FakePreferences("2", "Cat", "Green", LocalDateTime.now())

    webTestClient.put()
      .uri("/fake-preferences")
      .bodyValue(body)
      .exchange()
      .expectStatus()
      .isOk

    val result = fakePreferencesRepository.findById(body.prisonerNumber)
    assertThat(result).isPresent
    assertThat(result.get().prisonerNumber).isEqualTo("2")
    assertThat(result.get().favouriteAnimal).isEqualTo("Cat")
    assertThat(result.get().favouriteColour).isEqualTo("Green")
    assertThat(result.get().lastUpdated).isEqualTo(body.lastUpdated)
  }

  @Test
  fun `Rejects invalid data`() {
    webTestClient.put()
      .uri("/fake-preferences")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue("{}")
      .exchange()
      .expectStatus()
      .isBadRequest
  }
}
