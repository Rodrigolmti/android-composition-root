package com.rodrigolmti.modules.features.home.data.mapper

import com.rodrigolmti.modules.features.home.data.response.ShortDrinkResponse
import com.rodrigolmti.modules.home.domain.model.ShortDrink

fun ShortDrinkResponse.toModel() = ShortDrink(
    idDrink = idDrink,
    strDrink = strDrink,
    strDrinkThumb = strDrinkThumb
)