package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model

import java.util.Date

data class FakePreferences(
  val favouriteAnimal: String,
  val favouriteColour: String,
  val lastUpdated: Date,
)
