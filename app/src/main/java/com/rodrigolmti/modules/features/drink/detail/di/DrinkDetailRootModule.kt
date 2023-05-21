package com.rodrigolmti.modules.features.drink.detail.di

import com.rodrigolmti.drink.detail.data.provider.IDrinkDetailNetworkProvider
import com.rodrigolmti.drink.detail.di.drinkDetailModule
import com.rodrigolmti.modules.features.drink.detail.data.network.DrinkDetailNetworkProvider
import com.rodrigolmti.modules.features.drink.detail.data.network.IDrinkDetailRetrofit
import org.koin.dsl.module
import retrofit2.Retrofit

internal val drinkDetailRootModule = module {
    factory<IDrinkDetailNetworkProvider> {
        DrinkDetailNetworkProvider(get<Retrofit>().create(IDrinkDetailRetrofit::class.java))
    }
    includes(drinkDetailModule)
}