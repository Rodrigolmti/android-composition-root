package com.rodrigolmti.modules.features.home.data.network

import com.rodrigolmti.modules.home.data.network.IHomeNetworkProvider
import com.rodrigolmti.modules.home.data.response.BodyShortDrinkResponse
import retrofit2.http.GET

interface IHomeRetrofit {
    @GET("/api/json/v1/1/filter.php?a=Alcoholic")
    suspend fun getDrinks(): BodyShortDrinkResponse
}

class HomeNetworkProvider(private val retrofit: IHomeRetrofit) : IHomeNetworkProvider {

    override suspend fun getDrinks(): BodyShortDrinkResponse = retrofit.getDrinks()
}