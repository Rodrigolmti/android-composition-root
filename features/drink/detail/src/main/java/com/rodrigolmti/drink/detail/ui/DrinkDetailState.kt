package com.rodrigolmti.drink.detail.ui

import com.rodrigolmti.drink.detail.domain.model.DrinkDetail

sealed interface DrinkDetailState {
    object Loading : DrinkDetailState
    data class Success(val data: DrinkDetail) : DrinkDetailState
    object Error : DrinkDetailState
}