package com.violet.features.auth.routes

import com.violet.email.data.AppSecrets
import com.violet.email.data.EmailData
import com.violet.email.data.EmailService
import com.violet.features.auth.models.SignupResponse
import com.violet.features.users.models.NewUser
import com.violet.features.users.models.SimpleUser
import com.violet.features.users.models.UserData
import com.violet.features.users.repository.UsersRepository
import com.violet.shared.RepositoriesTags
import common.data.Endpoints
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import java.util.Random

internal fun Routing.signupRoute(
    usersRepository: UsersRepository,
    secrets: AppSecrets,
    emailService: EmailService
) {
    route(Endpoints.Auth.Signup) {
        install(NotarizedRoute()) {
            tags = setOf(RepositoriesTags.AUTH)
            post = PostInfo.builder {
                summary("User signup")
                description("Sign up a user with their email and password")
                request {
                    description("Register a user")
                    requestType<UserData>()
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
            usersRepository.readByEmail(user.email)?.let {
                call.respond(HttpStatusCode.BadRequest, "User already exists")
                return@post
            }
            if (secrets.smtpSupported) {
                usersRepository.create(
                    NewUser(
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
                usersRepository.create(
                    NewUser(
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

