package com.violet.users

import com.violet.email.data.AppSecrets
import com.violet.users.data.DefaultUserService
import com.violet.users.data.UserService
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

fun usersModule(secrets: AppSecrets) = module {
    single {
        Database.connect(
            url = "jdbc:postgresql://localhost:${secrets.dbPort}/${secrets.dbName}",
            user = secrets.dbUser,
            driver = "org.h2.Driver",
            password = secrets.dbPassword
        )
    }
    single<UserService> { DefaultUserService(get()) }
}