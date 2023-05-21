package com.rodrigolmti.modules.home.data.network

import com.rodrigolmti.modules.home.domain.model.ShortDrink

interface IHomeNetworkProvider {

    suspend fun getDrinks(): List<ShortDrink>
}