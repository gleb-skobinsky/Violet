package org.violet.violetapp.common.network

import org.jetbrains.compose.resources.getString
import org.violet.violetapp.resources.AppRes
import org.violet.violetapp.resources.generic_eror

suspend fun <T : Any> genericError(): RequestResult.Error<T> {
    return RequestResult.Error(getString(AppRes.string.generic_eror))
}