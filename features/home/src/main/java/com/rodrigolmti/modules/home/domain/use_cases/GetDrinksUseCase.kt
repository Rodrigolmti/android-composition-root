package com.rodrigolmti.modules.home.domain.use_cases

import com.rodrigolmti.modules.home.core.Resource
import com.rodrigolmti.modules.home.domain.model.ShortDrink

fun interface GetDrinksUseCase : suspend () -> Resource<List<ShortDrink>, Unit>