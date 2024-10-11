import org.gradle.kotlin.dsl.kapt

val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("kapt") version "2.0.20"
    id("io.ktor.plugin") version "3.0.0-rc-2"
    application
    kotlin("plugin.serialization") version "2.0.20"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor Server Core et Netty pour la version 2.2.4
    implementation("io.ktor:ktor-server-core:2.2.4")
    implementation("io.ktor:ktor-server-netty:2.2.4")

    implementation("com.google.dagger:dagger:2.52")
    kapt("com.google.dagger:dagger-compiler:2.52")
    // Ktor Serialization avec kotlinx-serialization pour la version 2.2.4
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")

    // Logback pour la gestion des logs
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Exposed pour l'ORM et PostgreSQL
    implementation("org.jetbrains.exposed:exposed-core:0.37.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.2")
    implementation("org.postgresql:postgresql:42.7.2")

    // Ktor Content Negotiation pour gérer JSON dans Ktor 2.x
    implementation("io.ktor:ktor-server-content-negotiation:2.2.4")

    // Tests
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.2.4")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
