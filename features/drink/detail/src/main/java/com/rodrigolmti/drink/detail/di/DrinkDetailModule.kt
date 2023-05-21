package com.rodrigolmti.drink.detail.di

import com.rodrigolmti.drink.detail.data.data_source.DrinkDetailDataSource
import com.rodrigolmti.drink.detail.data.data_source.IDrinkDetailDataSource
import com.rodrigolmti.drink.detail.domain.use_case.GetDrinkByIdUseCase
import com.rodrigolmti.drink.detail.ui.DrinkDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drinkDetailModule = module {
    factory<IDrinkDetailDataSource> { DrinkDetailDataSource(get()) }
    factory {
        GetDrinkByIdUseCase {
            get<IDrinkDetailDataSource>().getDrinkById(it)
        }
    }
    viewModel { DrinkDetailViewModel(get()) }
}