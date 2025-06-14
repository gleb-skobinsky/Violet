package org.violet.violetapp.auth.data

import org.violet.violetapp.secureStorage.Vault

private data object SecureStorageKeys {
    const val USER_EMAIL = "user_email"
    const val USER_ACCESS_TOKEN = "user_access_token"
}

interface UserSecureStorage {
    suspend fun saveEmail(email: String)

    suspend fun saveToken(token: String)

    suspend fun getEmail(): String?

    suspend fun getToken(): String?

    suspend fun clearAll()
}

class UserSecureStorageImpl(
    private val vault: Vault
) : UserSecureStorage {
    override suspend fun saveEmail(email: String) {
        vault.saveStr(SecureStorageKeys.USER_EMAIL, email)
    }

    override suspend fun saveToken(token: String) {
        vault.saveStr(SecureStorageKeys.USER_ACCESS_TOKEN, token)
    }

    override suspend fun getEmail(): String? {
        return vault.string(SecureStorageKeys.USER_EMAIL)
    }

    override suspend fun getToken(): String? {
        return vault.string(SecureStorageKeys.USER_ACCESS_TOKEN)
    }

    override suspend fun clearAll() {
        vault.clear()
    }
}