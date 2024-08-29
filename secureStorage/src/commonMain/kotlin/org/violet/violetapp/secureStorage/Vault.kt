package org.violet.violetapp.secureStorage

interface Vault {
    fun string(key: String): String?

    fun saveStr(key: String, value: String)

    fun clear()
}