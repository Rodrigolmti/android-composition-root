package com.rodrigolmti.modules.di

import com.rodrigolmti.modules.features.drink.detail.di.drinkDetailRootModule
import com.rodrigolmti.modules.features.home.di.homeRootModule
import com.rodrigolmti.modules.network.di.networkModule
import org.koin.dsl.module

internal val applicationModule = module {
    includes(networkModule, homeRootModule, drinkDetailRootModule)
}