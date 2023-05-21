package com.rodrigolmti.modules.home.di

import com.rodrigolmti.modules.home.entry.HomeFeatureEntry
import com.rodrigolmti.modules.home.entry.IHomeFeatureEntry
import com.rodrigolmti.modules.home.data.data_source.IRemoteHomeDataSource
import com.rodrigolmti.modules.home.data.data_source.RemoteHomeDataSource
import com.rodrigolmti.modules.home.domain.use_case.GetDrinksUseCase
import com.rodrigolmti.modules.home.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory<IRemoteHomeDataSource> { RemoteHomeDataSource(get()) }
    factory {
        GetDrinksUseCase(get<IRemoteHomeDataSource>()::getDrinks)
    }
    factory<IHomeFeatureEntry>{ HomeFeatureEntry() }
    viewModel { HomeViewModel(get()) }
}