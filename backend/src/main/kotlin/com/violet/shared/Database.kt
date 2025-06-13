package com.violet.shared

import com.violet.email.data.AppSecrets
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module

val databaseModule = module {
    single<Database> {
        val secrets: AppSecrets = get()
        println("Db user: ${secrets.dbUser} ${secrets.dbPassword}")
        Database.connect(
            url = "jdbc:postgresql://localhost:${secrets.dbPort}/${secrets.dbName}",
            user = secrets.dbUser,
            driver = "org.h2.Driver",
            password = secrets.dbPassword
        )
    }
}