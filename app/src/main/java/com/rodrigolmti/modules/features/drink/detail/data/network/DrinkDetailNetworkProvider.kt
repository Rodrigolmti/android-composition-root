package com.rodrigolmti.modules.features.drink.detail.data.network

import com.rodrigolmti.drink.detail.data.provider.IDrinkDetailNetworkProvider
import com.rodrigolmti.drink.detail.domain.model.DrinkDetail
import com.rodrigolmti.modules.features.drink.detail.data.mapper.toModel
import com.rodrigolmti.modules.features.drink.detail.data.response.BodyDetailDrinkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDrinkDetailRetrofit {

    @GET("/api/json/v1/1/lookup.php?")
    suspend fun getDrinks(
        @Query(value = "i") id: String
    ): BodyDetailDrinkResponse
}

class DrinkDetailNetworkProvider(
    private val retrofit: IDrinkDetailRetrofit
) : IDrinkDetailNetworkProvider {

    override suspend fun getDrinkDetail(id: String): DrinkDetail? = retrofit.getDrinks(id).toModel()
}