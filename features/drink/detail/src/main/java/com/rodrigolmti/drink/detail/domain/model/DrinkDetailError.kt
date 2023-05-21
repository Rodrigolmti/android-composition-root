package com.rodrigolmti.drink.detail.domain.model

sealed interface DrinkDetailError {
    object NetworkError : DrinkDetailError
    object DrinkNotFound : DrinkDetailError
}