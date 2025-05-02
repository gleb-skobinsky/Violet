package com.violet.features.users.repository

import com.violet.features.users.models.ExposedUser

interface UsersRepository {

    suspend fun create(user: ExposedUser): String

    suspend fun readById(id: String): ExposedUser?

    suspend fun readByEmail(email: String): ExposedUser?

    suspend fun update(id: String, user: ExposedUser)

    suspend fun delete(id: String)
}