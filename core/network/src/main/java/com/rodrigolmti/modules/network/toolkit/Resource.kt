package com.rodrigolmti.modules.network.toolkit

sealed class Resource<out D, out E> {
    data class Success<D>(val value: D) : Resource<D, Nothing>()
    data class Error<E>(val value: E) : Resource<Nothing, E>()

    inline fun fold(
        onSuccess: (result: D) -> Unit = {},
        onError: (error: E) -> Unit = {},
        onFinish: (D?) -> Unit = {}
    ): D? = when (this) {
        is Success -> {
            onSuccess(value)
            onFinish(value)
            value
        }

        is Error -> {
            onError(value)
            onFinish(null)
            null
        }
    }

    inline fun <T> mapSuccess(transform: (D) -> T): Resource<T, E> = when (this) {
        is Success -> Success(transform(value))
        is Error -> Error(value)
    }

    inline fun <T> mapError(transform: (E) -> T): Resource<D, T> = when (this) {
        is Success -> Success(value)
        is Error -> Error(transform(value))
    }

    inline fun <T, F> flatMap(
        transformSuccess: (D) -> Resource<T, F>,
        transformError: (E) -> Resource<T, F>
    ): Resource<T, F> = when (this) {
        is Success -> transformSuccess(value)
        is Error -> transformError(value)
    }
}