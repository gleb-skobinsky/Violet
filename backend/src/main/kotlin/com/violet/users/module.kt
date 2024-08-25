package com.violet.users

import com.violet.email.data.AppSecrets
import com.violet.users.data.DefaultUserService
import com.violet.users.data.UserService
import org.jetbrains.exposed.sql.Database
import org.koin.core.module.Module
import org.koin.dsl.module

val usersModule: Module = module {
    single {
        val secrets: AppSecrets = get()
        Database.connect(
            url = "jdbc:postgresql://localhost:${secrets.dbPort}/${secrets.dbName}",
            user = secrets.dbUser,
            driver = "org.h2.Driver",
            password = secrets.dbPassword
        )
    }
    single<UserService> { DefaultUserService(get()) }
}