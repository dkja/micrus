package org.example

import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.slf4j.event.Level

internal fun Application.configureMonitoring() {
    configureCallLogging()
    configureMetrics()
}

private fun Application.configureCallLogging() = install(CallLogging) {
    level = Level.INFO
}

private fun Application.configureMetrics() {
    val registry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    install(MicrometerMetrics) { this.registry = registry }

    routing {
        get("/metrics") { call.respond(registry.scrape()) }
    }
}