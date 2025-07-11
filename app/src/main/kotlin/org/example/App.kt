package org.example

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main(): Unit = runBlocking {
    embeddedServer(CIO, port = 8080) {
        routing {
            get("/") {
                call.respondText("OK")
            }
            get("/clock") {
                val now = Clock.System.now()
                val content = now.toString()
                call.respondText(content)
            }
        }
    }.startSuspend(wait = true)
}
