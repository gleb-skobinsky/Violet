package com.violet.database

import com.violet.plugins.UserService
import org.jetbrains.exposed.sql.Database

object UserServiceProvider {
    private val database by lazy {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "root",
            driver = "org.h2.Driver",
            password = ""
        )
    }
    val service = UserService(database)
}
