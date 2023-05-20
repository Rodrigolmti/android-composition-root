package com.rodrigolmti.modules.home.ui.home

import com.rodrigolmti.modules.home.domain.model.ShortDrink

sealed interface HomeViewState {
    object Loading : HomeViewState
    object Error : HomeViewState
    data class Success(val drinks: List<ShortDrink>) : HomeViewState
}