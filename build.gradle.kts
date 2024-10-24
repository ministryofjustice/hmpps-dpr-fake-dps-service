plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "5.15.6"
  kotlin("plugin.spring") version "2.0.21"
  kotlin("plugin.jpa") version "2.0.20"
  id("jacoco")
  id("org.barfuin.gradle.jacocolog") version "3.1.0"
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  // DPR Library
  implementation("uk.gov.justice.service.hmpps:hmpps-digital-prison-reporting-lib:5.0.0")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

  // Database
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.postgresql:postgresql:42.7.4")

  // Swagger
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

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
