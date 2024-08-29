package org.violet.violetapp.common.network

import org.jetbrains.compose.resources.getString
import violet.composeapp.generated.resources.Res
import violet.composeapp.generated.resources.generic_eror

suspend fun <T : Any> genericError(): RequestResult.Error<T> {
    return RequestResult.Error(getString(Res.string.generic_eror))
}