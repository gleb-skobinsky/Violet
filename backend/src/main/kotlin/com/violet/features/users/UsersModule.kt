package com.violet.features.users

import com.violet.features.users.repository.DefaultUsersRepository
import com.violet.features.users.repository.UsersRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val usersModule: Module = module {
    single<UsersRepository> {
        DefaultUsersRepository(get())
    }
}