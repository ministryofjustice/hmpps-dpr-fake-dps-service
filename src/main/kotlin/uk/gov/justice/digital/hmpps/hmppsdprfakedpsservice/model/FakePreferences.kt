package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Entity
@Table(name = "fake_preferences")
data class FakePreferences(

  @Id
  val prisonerNumber: String,

  val favouriteAnimal: String,

  val favouriteColour: String,

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val lastUpdated: LocalDateTime,

)
