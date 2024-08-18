package com.violet.users.data

interface UserService {

    suspend fun create(user: ExposedUser): Int

    suspend fun readById(id: Int): ExposedUser?

    suspend fun readByEmail(email: String): ExposedUser?

    suspend fun update(id: Int, user: ExposedUser)

    suspend fun delete(id: Int)
}