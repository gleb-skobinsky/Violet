package org.violet.violetapp.auth.data

import org.violet.violetapp.secureStorage.Vault

private data object SecureStorageKeys {
    const val USER_EMAIL = "user_email"
    const val USER_ACCESS_TOKEN = "user_access_token"
}

interface UserSecureStorage {
    fun saveEmail(email: String)

    fun saveToken(token: String)

    fun getEmail(): String?

    fun getToken(): String?

    fun clearAll()
}

class UserSecureStorageImpl(
    private val vault: Vault
) : UserSecureStorage {
    override fun saveEmail(email: String) {
        vault.saveStr(SecureStorageKeys.USER_EMAIL, email)
    }

    override fun saveToken(token: String) {
        vault.saveStr(SecureStorageKeys.USER_ACCESS_TOKEN, token)
    }

    override fun getEmail(): String? {
        return vault.string(SecureStorageKeys.USER_EMAIL)
    }

    override fun getToken(): String? {
        return vault.string(SecureStorageKeys.USER_ACCESS_TOKEN)
    }

    override fun clearAll() {
        vault.clear()
    }
}