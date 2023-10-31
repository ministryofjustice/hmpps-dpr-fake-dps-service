package uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication()
@ComponentScan(
  "uk.gov.justice.digital.hmpps.hmppsdprfakedpsservice",
  "uk.gov.justice.digital.hmpps.digitalprisonreportinglib"
)
class HmppsDprFakeDpsService

fun main(args: Array<String>) {
  runApplication<HmppsDprFakeDpsService>(*args)
}
