package com.rodrigolmti.modules

import android.app.Application
import com.rodrigolmti.modules.di.applicationModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(applicationModule)
        }
    }
}