package com.rodrigolmti.drink.detail.data.provider

import com.rodrigolmti.drink.detail.domain.model.DrinkDetail

interface IDrinkDetailNetworkProvider {
    suspend fun getDrinkDetail(id: String): DrinkDetail?
}