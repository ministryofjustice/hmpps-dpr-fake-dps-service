package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.data.FakePreferencesRepository
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model.FakePreferences

@RestController
@Tag(name = "Fake Preferences API")
class FakePreferencesController(val fakePreferencesRepository: FakePreferencesRepository) {
  @Operation(description = "Gets a list of prisoners' fake preferences")
  @GetMapping("/fake-preferences")
  fun listFakePreferences(): List<FakePreferences> {
    return fakePreferencesRepository.findAll().toList()
  }

  @Operation(description = "Adds a prisoner's fake preferences")
  @PutMapping("/fake-preferences")
  fun putFakePreferences(
    @RequestBody
    @Valid
    fakePreferences: FakePreferences,
  ) {
    fakePreferencesRepository.save(fakePreferences)
  }
}
