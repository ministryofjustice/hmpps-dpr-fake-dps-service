plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "5.+"
  kotlin("plugin.spring") version "1.9.22"
  kotlin("plugin.jpa") version "1.9.22"
  id("jacoco")
  id("org.barfuin.gradle.jacocolog") version "3.1.0"
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  // DPR Library
  implementation("uk.gov.justice.service.hmpps:hmpps-digital-prison-reporting-lib:3.0.9")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

  // Database
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.postgresql:postgresql")

  // Swagger
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

  // Testing
  testImplementation("com.h2database:h2")
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(19))
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "19"
    }
  }
}

tasks.test {
  finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
  dependsOn(tasks.test)
}
