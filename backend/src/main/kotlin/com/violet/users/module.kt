package com.violet.users

import com.violet.users.data.DefaultUserService
import com.violet.users.data.UserService
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val usersModule = module {
    single {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "root",
            driver = "org.h2.Driver",
            password = ""
        )
    }
    single<UserService> { DefaultUserService(get()) }
}