package com.rodrigolmti.modules

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.rodrigolmti.modules.di.applicationModule
import com.rodrigolmti.modules.di.getUnsafeOkHttpClient
import okhttp3.Call
import org.koin.android.BuildConfig
import org.koin.android.ext.android.get
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(applicationModule)
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .callFactory(get<Call.Factory>())
        .respectCacheHeaders(false)
        .okHttpClient {
            getUnsafeOkHttpClient()
        }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()
}