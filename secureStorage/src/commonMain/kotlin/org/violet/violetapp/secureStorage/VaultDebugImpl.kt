package org.violet.violetapp.secureStorage

class VaultDebugImpl : Vault {
    private val map: MutableMap<String, String> = mutableMapOf()

    override fun string(key: String): String? {
        return map[key]
    }

    override fun saveStr(key: String, value: String) {
        map[key] = value
    }

    override fun clear() {
        map.clear()
    }
}