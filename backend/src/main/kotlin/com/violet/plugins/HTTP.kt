package com.violet.plugins

import io.bkbn.kompendium.core.plugin.NotarizedApplication
import io.bkbn.kompendium.oas.OpenApiSpec
import io.bkbn.kompendium.oas.info.Contact
import io.bkbn.kompendium.oas.info.Info
import io.bkbn.kompendium.oas.info.License
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import java.net.URI

fun Application.configureHTTP() {
    install(NotarizedApplication()) {
        spec = OpenApiSpec(
            openapi = "3.0.3",
            info = Info(
                title = "Violet API",
                version = "1.0.0",
                summary = "Swagger documentation for the Violet backend API",
                description = "Here you can find all the necessary endpoints to consume the Violet API",
                termsOfService = URI.create("https://example.com"),
                contact = Contact("Gleb Gutnik"),
                license = License("Open license")
            )
        )
    }
    routing {
        swaggerUI(path = "openapi", swaggerFile = "openapi/openapi.yaml")
    }
}
