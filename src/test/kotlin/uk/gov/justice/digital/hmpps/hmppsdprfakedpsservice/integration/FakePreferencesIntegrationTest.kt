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

class FakePreferencesIntegrationTest : IntegrationTestBase() {

  @Autowired
  lateinit var fakePreferencesRepository: FakePreferencesRepository

  @BeforeEach
  fun clearData() {
    fakePreferencesRepository.deleteAll()
  }

  @Test
  fun `Get list returns empty array successfully`() {
    webTestClient.get()
      .uri("/fake-preferences")
      .exchange()
      .expectStatus()
      .isOk
      .expectBody()
      .json("[]")
  }

  @Test
  fun `Get list returns data correctly`() {
    val expected = FakePreferences("1", "Cat", "Green", LocalDateTime.now())

    fakePreferencesRepository.save(expected)

    val result = webTestClient.get()
      .uri("/fake-preferences")
      .exchange()
      .expectStatus()
      .isOk
      .returnResult<FakePreferences>()
      .responseBody
      .blockLast()!!

    assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `Get by ID returns data correctly`() {
    val expected = FakePreferences("3", "Frog", "Purple", LocalDateTime.now())

    fakePreferencesRepository.save(expected)

    val result = webTestClient.get()
      .uri("/fake-preferences/3")
      .exchange()
      .expectStatus()
      .isOk
      .returnResult<FakePreferences>()
      .responseBody
      .blockLast()!!

    assertThat(result).isEqualTo(expected)
  }

  @Test
  fun `Get by ID returns Not Found for missing preference`() {
    webTestClient.get()
      .uri("/fake-preferences/4")
      .exchange()
      .expectStatus()
      .isNotFound
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
    assertThat(result.get()).isEqualTo(body)
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

  @Test
  fun `Delete all works correctly`() {
    fakePreferencesRepository.save(FakePreferences("1", "Cat", "Green", LocalDateTime.now()))
    fakePreferencesRepository.save(FakePreferences("2", "Dog", "Blue", LocalDateTime.now()))

    assertThat(fakePreferencesRepository.count()).isEqualTo(2)

    webTestClient.delete()
      .uri("/fake-preferences")
      .exchange()
      .expectStatus()
      .isOk

    assertThat(fakePreferencesRepository.count()).isEqualTo(0)
  }

  @Test
  fun `Delete by ID works correctly`() {
    fakePreferencesRepository.save(FakePreferences("3", "Frog", "Purple", LocalDateTime.now()))

    webTestClient.delete()
      .uri("/fake-preferences/3")
      .exchange()
      .expectStatus()
      .isOk

    assertThat(fakePreferencesRepository.count()).isEqualTo(0)
  }

  @Test
  fun `Delete by ID returns Not Found for missing preference`() {
    webTestClient.delete()
      .uri("/fake-preferences/4")
      .exchange()
      .expectStatus()
      .isNotFound
  }
}
