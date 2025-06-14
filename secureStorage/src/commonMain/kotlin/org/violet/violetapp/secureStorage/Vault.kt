package org.violet.violetapp.secureStorage

interface Vault {
    suspend fun string(key: String): String?

    suspend fun saveStr(key: String, value: String)

    suspend fun clear()
}