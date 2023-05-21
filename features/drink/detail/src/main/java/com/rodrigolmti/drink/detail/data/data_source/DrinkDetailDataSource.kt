package com.rodrigolmti.drink.detail.data.data_source

import com.rodrigolmti.drink.detail.data.provider.IDrinkDetailNetworkProvider
import com.rodrigolmti.drink.detail.domain.model.DrinkDetail
import com.rodrigolmti.drink.detail.domain.model.DrinkDetailError
import com.rodrigolmti.modules.network.toolkit.Resource
import com.rodrigolmti.modules.network.toolkit.execute

interface IDrinkDetailDataSource {
    suspend fun getDrinkById(id: String): Resource<DrinkDetail, DrinkDetailError>
}

class DrinkDetailDataSource(private val network: IDrinkDetailNetworkProvider) :
    IDrinkDetailDataSource {

    override suspend fun getDrinkById(id: String): Resource<DrinkDetail, DrinkDetailError> =
        execute {
            network.getDrinkDetail(id)
        }.flatMap(
            {
                it?.let { Resource.Success(it) } ?: Resource.Error(DrinkDetailError.DrinkNotFound)
            }, {
                Resource.Error(DrinkDetailError.NetworkError)
            }
        )
}