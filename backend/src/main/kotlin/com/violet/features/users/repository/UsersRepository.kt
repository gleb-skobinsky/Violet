package com.violet.features.users.repository

import com.violet.features.users.models.ExistingUser
import com.violet.features.users.models.NewUser

interface UsersRepository {

    suspend fun create(user: NewUser): String

    suspend fun readById(id: String): ExistingUser?

    suspend fun readByEmail(email: String): ExistingUser?

    suspend fun update(id: String, user: ExistingUser)

    suspend fun delete(id: String)
}