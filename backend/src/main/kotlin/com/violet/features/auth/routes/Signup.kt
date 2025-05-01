package com.violet.features.auth.routes

import com.violet.email.data.AppSecrets
import com.violet.email.data.EmailData
import com.violet.email.data.EmailService
import com.violet.features.auth.models.SignupResponse
import com.violet.users.data.ExposedUser
import com.violet.users.data.SimpleUser
import com.violet.users.data.UserService
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

internal fun Routing.signupRoute(
    userService: UserService,
    secrets: AppSecrets,
    emailService: EmailService
) {
    route("/signup") {
        install(NotarizedRoute()) {
            tags = setOf("Auth Repository")
            post = PostInfo.builder {
                summary("User signup")
                description("Sign up a user with their email and password")
                request {
                    description("Register a user")
                    requestType<SimpleUser>()
                }
                response {
                    description("User successfully registered")
                    responseCode(HttpStatusCode.Created)
                    responseType<SignupResponse>()
                }
            }
        }
        post {
            val user = call.receive<SimpleUser>()
            userService.readByEmail(user.email)?.let {
                call.respond(HttpStatusCode.BadRequest, "User already exists")
                return@post
            }
            if (secrets.smtpSupported) {
                userService.create(
                    ExposedUser(
                        email = user.email,
                        password = user.password,
                        verified = false
                    )
                )
                val emailData = EmailData(
                    emailFrom = secrets.emailFrom,
                    emailTo = user.email,
                    message = "Verify your email using the following code: ${Random().nextInt(999999)}",
                    subject = "Email verification"
                )
                emailService.sendEmail2(emailData)
                call.respond(
                    HttpStatusCode.Created,
                    SignupResponse(
                        emailSent = true,
                        message = "Check your inbox for a verification email"
                    )
                )
            } else {
                userService.create(
                    ExposedUser(
                        email = user.email,
                        password = user.password,
                        verified = true
                    )
                )
                call.respond(
                    HttpStatusCode.Created,
                    SignupResponse(
                        emailSent = false,
                        message = "User registration has been successful"
                    )
                )
            }
        }
    }
}

