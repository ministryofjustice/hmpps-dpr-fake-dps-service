package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.data

import org.springframework.data.repository.CrudRepository
import uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice.model.FakePreferences

interface FakePreferencesRepository : CrudRepository<FakePreferences, String>
