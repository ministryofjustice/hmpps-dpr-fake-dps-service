package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.data.FakePreferencesRepository
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model.FakePreferences
import kotlin.jvm.optionals.getOrElse

@RestController
@Tag(name = "Fake Preferences API")
class FakePreferencesController(val fakePreferencesRepository: FakePreferencesRepository) {
  @Operation(description = "Gets a list of prisoners' fake preferences")
  @GetMapping("/fake-preferences")
  fun listFakePreferences(): List<FakePreferences> {
    return fakePreferencesRepository.findAll().toList()
  }

  @Operation(description = "Gets a specific prisoner's fake preferences")
  @GetMapping("/fake-preferences/{prisonerNumber}")
  fun getFakePreferences(@PathVariable prisonerNumber: String): FakePreferences {
    return fakePreferencesRepository.findById(prisonerNumber)
      .getOrElse {
        log.warn("Preferences not found: {}", prisonerNumber)
        throw EntityNotFoundException()
      }
  }

  @Operation(description = "Adds or updates a prisoner's fake preferences")
  @PutMapping("/fake-preferences")
  fun putFakePreferences(
    @RequestBody
    @Valid
    fakePreferences: FakePreferences,
  ) {
    fakePreferencesRepository.save(fakePreferences)
  }

  companion object {
    private val log = LoggerFactory.getLogger(this::class.java)
  }

  @Operation(description = "Deletes all prisoners' fake preferences")
  @DeleteMapping("/fake-preferences")
  fun deleteFakePreferences() {
    fakePreferencesRepository.deleteAll()
  }

  @Operation(description = "Gets a specific prisoner's fake preferences")
  @DeleteMapping("/fake-preferences/{prisonerNumber}")
  fun deleteFakePreferences(@PathVariable prisonerNumber: String) {
    if (!fakePreferencesRepository.existsById(prisonerNumber)) {
      log.warn("Preferences not found: {}", prisonerNumber)
      throw EntityNotFoundException()
    }

    fakePreferencesRepository.deleteById(prisonerNumber)
  }
}
