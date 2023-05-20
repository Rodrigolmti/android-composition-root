package com.rodrigolmti.modules.home.core

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
}