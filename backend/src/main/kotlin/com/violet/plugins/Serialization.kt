package com.violet.plugins

import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/json/kotlinx-serialization") {
            install(NotarizedRoute()) {
                get = GetInfo.builder {
                    summary("Serialization test")
                    description("Hello world from kotlinx serialization")
                    response {
                        description("Check success")
                        responseCode(HttpStatusCode.OK)
                        responseType<Map<String, String>>()
                    }
                }
            }

            get {
                call.respond(mapOf("hello" to "world"))
            }
        }
    }
}
