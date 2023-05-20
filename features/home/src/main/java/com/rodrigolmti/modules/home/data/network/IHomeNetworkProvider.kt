package com.rodrigolmti.modules.home.data.network

import com.rodrigolmti.modules.home.data.response.BodyShortDrinkResponse

interface IHomeNetworkProvider {

    suspend fun getDrinks(): BodyShortDrinkResponse
}