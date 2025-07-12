plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.ktor.server.html.builder)

    implementation(libs.logback.classic)
    implementation(libs.micrometer.registry.prometheus)
    implementation("io.ktor:ktor-server-core:3.2.1")
    implementation("io.ktor:ktor-server-core:3.2.1")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.example.AppKt"
}
