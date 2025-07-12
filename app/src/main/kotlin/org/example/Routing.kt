package org.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.random.Random
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title(content = "Dzień dobry")
                }
                body {
                    h3 { +"Dzień dobry Moja Droga :*" }
                }
            }
        }
        get("/clock") {

            val polishZoneId = ZoneId.of("Europe/Warsaw")
            val now = ZonedDateTime.now(polishZoneId)

            val formatted = now.toString()
            call.respondText(formatted)
        }
        post("/user/{id}") {
            val parameter = call.parameters["id"]
            val id = Random.Default.nextInt(1000)

            call.respondText("Your ID for $parameter = $id", status = HttpStatusCode.Created)
        }
    }
}