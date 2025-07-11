package org.example

import io.ktor.server.application.Application
import io.ktor.server.cio.*
import io.ktor.server.engine.*

suspend fun main() {
    embeddedServer(CIO, port = 8080, module = Application::application)
        .startSuspend(wait = true)
}

private fun Application.application() {
    configureMonitoring()
    configureRouting()
}