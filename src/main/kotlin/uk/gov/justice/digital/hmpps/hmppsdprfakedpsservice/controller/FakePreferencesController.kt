package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model.FakePreferences

@RestController
@Tag(name = "Establishments API")
class FakePreferencesController {
  @Operation(description = "Gets a list of fake preferences")
  @GetMapping("/fake-preferences")
  fun listFakePreferences(): List<FakePreferences> {
    return emptyList()
  }
}
