package org.example

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("OK")
        }
        get("/clock") {

            val polishZoneId = ZoneId.of("Europe/Warsaw")
            val now = ZonedDateTime.now(polishZoneId)

            val formatted = now.toString()
            call.respondText(formatted)
        }
        post("user/{id}") {
            val parameter = call.parameters["id"]
            val id = Random.Default.nextInt(1000)

            call.respondText("Your ID for $parameter = $id", status = HttpStatusCode.Created)
        }
    }
}