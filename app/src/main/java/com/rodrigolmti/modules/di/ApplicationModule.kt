package com.rodrigolmti.modules.di

import com.rodrigolmti.modules.features.home.di.homeRootModule
import org.koin.dsl.module

internal val applicationModule = module {
    includes(networkModule, homeRootModule)
}