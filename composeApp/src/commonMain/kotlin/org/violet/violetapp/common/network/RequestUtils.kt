package org.violet.violetapp.common.network


inline fun <IN : Any, OUT : Any> RequestResult<IN>.mapOnSuccess(block: (IN) -> RequestResult<OUT>): RequestResult<OUT> =
    when (this) {
        is RequestResult.Success -> block(data)
        is RequestResult.Error -> RequestResult.Error(error = error)
    }

inline fun <T : Any> RequestResult<T>.onSuccess(block: (T) -> Unit): RequestResult<T> {
    when (this) {
        is RequestResult.Error -> Unit
        is RequestResult.Success -> block(data)
    }
    return this
}

inline fun <T : Any> RequestResult<T>.onError(block: (error: ErrorData) -> Unit): RequestResult<T> {
    when (this) {
        is RequestResult.Error -> block(error)
        is RequestResult.Success -> Unit
    }
    return this
}

inline fun <T : Any> RequestResult<T>.onAny(block: () -> Unit): RequestResult<T> {
    block.invoke()
    return this
}