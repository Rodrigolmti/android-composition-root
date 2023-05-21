package com.rodrigolmti.modules.network.toolkit

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

sealed class ResultError(val message: String?) {
    class NetworkError(
        val httpCode: Int = -1,
        val httpMessage: String? = null,
        val exceptionTitle: String? = null,
        val exceptionMessage: String? = null,
        val isConnectionError: Boolean = false,
        val expectedAction: String? = null,
    ) : ResultError(httpMessage)
}

internal fun Throwable.isConnectionException(): Boolean =
    this is ConnectException || this is UnknownHostException || this is ConnectionShutdownException

suspend fun <T> execute(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T,
): Resource<T, ResultError.NetworkError> {
    return withContext(dispatcher) {
        try {
            mapResponse(apiCall())
        } catch (exception: Exception) {
            if (exception is HttpException) {
                mapHttpExceptionToResultError(
                    exception.code(),
                    exception.message()
                )
            } else {
                mapGenericExceptionToResultError(exception)
            }
        }
    }
}

private fun <T> mapResponse(response: T): Resource<T, ResultError.NetworkError> {
    return if (response !is Response<*>) {
        Resource.Success(response)
    } else {
        if (response.isSuccessful) {
            Resource.Success(response)
        } else {
            mapHttpExceptionToResultError(
                response.code(),
                response.message()
            )
        }
    }
}

private fun mapHttpExceptionToResultError(
    statusCode: Int,
    message: String,
) = try {
    Resource.Error(
        ResultError.NetworkError(
            httpCode = statusCode,
            httpMessage = message,
            isConnectionError = false
        )
    )
} catch (exception: Exception) {
    mapGenericExceptionToResultError(exception)
}

private fun mapGenericExceptionToResultError(
    exception: Exception,
) = Resource.Error(
    ResultError.NetworkError(
        exceptionTitle = exception::class.simpleName,
        exceptionMessage = exception.message,
        isConnectionError = exception.isConnectionException()
    )
)
