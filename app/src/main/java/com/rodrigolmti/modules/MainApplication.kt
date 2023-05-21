package com.rodrigolmti.modules

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.rodrigolmti.modules.di.applicationModule
import com.rodrigolmti.modules.network.UnsafeOkHttpClient
import okhttp3.Call
import org.koin.android.BuildConfig
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@MainApplication)

            modules(applicationModule)
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .callFactory(get<Call.Factory>())
        .respectCacheHeaders(false)
        .okHttpClient {
            UnsafeOkHttpClient.unsafeOkHttpClient
        }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()
}