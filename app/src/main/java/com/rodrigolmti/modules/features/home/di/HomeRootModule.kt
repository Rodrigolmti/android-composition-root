package com.rodrigolmti.modules.features.home.di

import androidx.navigation.NavHostController
import com.rodrigolmti.drink.detail.entry.IDrinkDetailFeatureRouter
import com.rodrigolmti.modules.features.home.data.network.HomeNetworkProvider
import com.rodrigolmti.modules.features.home.data.network.IHomeRetrofit
import com.rodrigolmti.modules.features.home.navigator.HomeNavigator
import com.rodrigolmti.modules.home.data.provider.IHomeNetworkProvider
import com.rodrigolmti.modules.home.di.homeModule
import com.rodrigolmti.modules.home.ui.home.IHomeDelegate
import org.koin.dsl.module
import retrofit2.Retrofit

internal val homeRootModule = module {
    factory<IHomeNetworkProvider> {
        HomeNetworkProvider(get<Retrofit>().create(IHomeRetrofit::class.java))
    }
    factory<IHomeDelegate> { (navController: NavHostController) ->
        HomeNavigator(navController, get<IDrinkDetailFeatureRouter>())
    }
    includes(homeModule)
}