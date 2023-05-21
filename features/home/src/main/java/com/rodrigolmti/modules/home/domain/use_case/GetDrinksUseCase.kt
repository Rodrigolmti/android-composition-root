package com.rodrigolmti.modules.home.domain.use_case

import com.rodrigolmti.modules.home.domain.model.DrinksError
import com.rodrigolmti.modules.home.domain.model.ShortDrink
import com.rodrigolmti.modules.network.toolkit.Resource

fun interface GetDrinksUseCase : suspend () -> Resource<List<ShortDrink>, DrinksError>