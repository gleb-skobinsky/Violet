package com.violet.features.users.repository

import com.violet.features.users.models.ExposedUser

interface UsersRepository {

    suspend fun create(user: ExposedUser): Int

    suspend fun readById(id: Int): ExposedUser?

    suspend fun readByEmail(email: String): ExposedUser?

    suspend fun update(id: Int, user: ExposedUser)

    suspend fun delete(id: Int)
}