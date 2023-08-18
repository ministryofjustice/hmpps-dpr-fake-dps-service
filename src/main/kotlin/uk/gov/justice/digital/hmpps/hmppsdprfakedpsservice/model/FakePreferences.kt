package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity
@Table(name = "fake_preferences")
data class FakePreferences(

  @Id
  val prisonerNumber: String,

  val favouriteAnimal: String,

  val favouriteColour: String,

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val lastUpdated: LocalDateTime,

) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as FakePreferences

    if (prisonerNumber != other.prisonerNumber) return false
    if (favouriteAnimal != other.favouriteAnimal) return false
    if (favouriteColour != other.favouriteColour) return false

    // Millisecond precision is truncated by Spring's mapping.
    return lastUpdated.truncatedTo(ChronoUnit.SECONDS) == other.lastUpdated.truncatedTo(ChronoUnit.SECONDS)
  }

  override fun hashCode(): Int {
    var result = prisonerNumber.hashCode()
    result = 31 * result + favouriteAnimal.hashCode()
    result = 31 * result + favouriteColour.hashCode()
    result = 31 * result + lastUpdated.hashCode()
    return result
  }
}
