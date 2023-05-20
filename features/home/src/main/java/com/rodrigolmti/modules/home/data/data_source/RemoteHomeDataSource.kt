package com.rodrigolmti.modules.home.data.data_source

import com.rodrigolmti.modules.home.core.Resource
import com.rodrigolmti.modules.home.data.mapper.toModel
import com.rodrigolmti.modules.home.data.network.IHomeNetworkProvider
import com.rodrigolmti.modules.home.domain.model.ShortDrink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IRemoteHomeDataSource {
    suspend fun getDrinks(): Resource<List<ShortDrink>, Unit>
}

class RemoteHomeDataSource(private val network: IHomeNetworkProvider) : IRemoteHomeDataSource {

    override suspend fun getDrinks(): Resource<List<ShortDrink>, Unit> = withContext(Dispatchers.IO) {
        try {
            val drinks = network.getDrinks().drinks.map { it.toModel() }
            Resource.Success(drinks)
        } catch (e: Exception) {
            Resource.Error(Unit)
        }
    }
}