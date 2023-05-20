package com.rodrigolmti.modules.di

import android.content.Context
import coil.ImageLoader
import coil.util.DebugLogger
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.Call
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

internal val networkModule = module {
    single { okHttpCallFactory() }
    single { imageLoader(get(), get()) }
    single { networkJson() }
    single { retrofitFactory(get(), get()) }
}

fun networkJson(): Json = Json {
    ignoreUnknownKeys = true
}

private fun retrofitFactory(
    networkJson: Json,
    okHttpCallFactory: Call.Factory,
): Retrofit = Retrofit.Builder()
    .baseUrl("https://www.thecocktaildb.com")
    .callFactory(okHttpCallFactory)
    .client(getUnsafeOkHttpClient())
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

private fun imageLoader(
    okHttpCallFactory: Call.Factory,
    application: Context,
): ImageLoader = ImageLoader.Builder(application)
    .callFactory(okHttpCallFactory)
    .respectCacheHeaders(false)
    .apply {
        if (BuildConfig.DEBUG) {
            logger(DebugLogger())
        }
    }
    .build()

private fun getUnsafeOkHttpClient(): OkHttpClient {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        val trustManagerFactory: TrustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers: Array<TrustManager> =
            trustManagerFactory.trustManagers
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            "Unexpected default trust managers:" + trustManagers.contentToString()
        }

        val trustManager =
            trustManagers[0] as X509TrustManager


        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustManager)
        builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}