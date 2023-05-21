package com.rodrigolmti.modules.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rodrigolmti.modules.network.UnsafeOkHttpClient
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

val networkModule = module {
    single { okHttpCallFactory() }
    single { networkJson() }
    single { retrofitFactory(get(), get()) }
}

private fun networkJson(): Json = Json {
    ignoreUnknownKeys = true
}

private fun retrofitFactory(
    networkJson: Json,
    okHttpCallFactory: Call.Factory,
): Retrofit = Retrofit.Builder()
    .baseUrl("https://www.thecocktaildb.com")
    .callFactory(okHttpCallFactory)
    .client(UnsafeOkHttpClient.unsafeOkHttpClient)
    .addConverterFactory(
        networkJson.asConverterFactory("application/json".toMediaType()),
    )
    .build()

private fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor()
            .apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            },
    )
    .build()