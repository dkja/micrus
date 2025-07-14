plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    alias(libs.plugins.graalvm.buildtools.native)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.ktor.server.html.builder)

    implementation(libs.logback.classic)
    implementation(libs.micrometer.registry.prometheus)
}

application {
    mainClass = "org.example.AppKt"
}

graalvmNative {
    binaries.all {
        buildArgs.addAll(
            "-H:+InstallExitHandlers",
            "-H:+ReportUnsupportedElementsAtRuntime",
            "-H:+ReportExceptionStackTraces"
        )
    }
}