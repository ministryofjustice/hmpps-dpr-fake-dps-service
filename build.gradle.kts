import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "7.1.0"
  kotlin("plugin.jpa") version "2.1.10"
  kotlin("plugin.spring") version "2.1.10"
  id("jacoco")
  id("org.barfuin.gradle.jacocolog") version "3.1.0"
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  // DPR Library
  implementation("uk.gov.justice.service.hmpps:hmpps-digital-prison-reporting-lib:7.5.0")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

  // Database
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.postgresql:postgresql:42.7.5")

  // Swagger
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")

  // Testing
  testImplementation("com.h2database:h2")
}

kotlin {
  jvmToolchain(21)
  compilerOptions {
    freeCompilerArgs = listOf(
      // cannot validate items within lists without this
      // cf. https://youtrack.jetbrains.com/issue/KT-67909/Resolve-inconsistencies-with-Java-in-emitting-JVM-type-annotations
      "-Xemit-jvm-type-annotations",
    )
  }
}

tasks {
  withType<KotlinCompile> {
    compilerOptions.jvmTarget = JvmTarget.JVM_21
  }
}

tasks.test {
  finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
  dependsOn(tasks.test)
}
