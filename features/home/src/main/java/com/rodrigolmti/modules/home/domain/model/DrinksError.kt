package com.rodrigolmti.modules.home.domain.model

sealed interface DrinksError {
    object NetworkError : DrinksError
}