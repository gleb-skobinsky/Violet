package org.violet.violetapp.common.utils

import kotlin.properties.Delegates.notNull

fun <T : Any> promisedValue(): T {
    val delegated by notNull<T>()
    return delegated
}