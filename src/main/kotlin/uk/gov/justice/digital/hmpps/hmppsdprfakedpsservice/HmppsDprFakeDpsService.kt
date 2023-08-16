package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication()
class HmppsDprFakeDpsService

fun main(args: Array<String>) {
  runApplication<HmppsDprFakeDpsService>(*args)
}
