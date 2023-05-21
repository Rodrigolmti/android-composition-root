package com.rodrigolmti.modules.home.data.data_source

import com.rodrigolmti.modules.home.data.provider.IHomeNetworkProvider
import com.rodrigolmti.modules.home.domain.model.DrinksError
import com.rodrigolmti.modules.home.domain.model.ShortDrink
import com.rodrigolmti.modules.network.toolkit.Resource
import com.rodrigolmti.modules.network.toolkit.execute

interface IRemoteHomeDataSource {
    suspend fun getDrinks(): Resource<List<ShortDrink>, DrinksError>
}

class RemoteHomeDataSource(private val network: IHomeNetworkProvider) : IRemoteHomeDataSource {

    override suspend fun getDrinks(): Resource<List<ShortDrink>, DrinksError> = execute {
        network.getDrinks()
    }.mapSuccess { response ->
        response
    }.mapError {
        DrinksError.NetworkError
    }
}