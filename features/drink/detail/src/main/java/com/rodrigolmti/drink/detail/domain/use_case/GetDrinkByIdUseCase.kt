package com.rodrigolmti.drink.detail.domain.use_case

import com.rodrigolmti.drink.detail.domain.model.DrinkDetail
import com.rodrigolmti.drink.detail.domain.model.DrinkDetailError
import com.rodrigolmti.modules.network.toolkit.Resource

fun interface GetDrinkByIdUseCase : suspend (String) -> Resource<DrinkDetail, DrinkDetailError>