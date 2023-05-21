package com.rodrigolmti.modules.features.home.di

import com.rodrigolmti.modules.features.home.data.network.HomeNetworkProvider
import com.rodrigolmti.modules.features.home.data.network.IHomeRetrofit
import com.rodrigolmti.modules.home.data.provider.IHomeNetworkProvider
import com.rodrigolmti.modules.home.di.homeModule
import org.koin.dsl.module
import retrofit2.Retrofit

internal val homeRootModule = module {
    factory<IHomeNetworkProvider> {
        HomeNetworkProvider(get<Retrofit>().create(IHomeRetrofit::class.java))
    }
    includes(homeModule)
}