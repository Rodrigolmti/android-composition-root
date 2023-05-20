package com.rodrigolmti.modules.home.data.response

import kotlinx.serialization.Serializable

@Serializable
data class BodyShortDrinkResponse(
    val drinks: List<ShortDrinkResponse> = emptyList()
)

@Serializable
data class ShortDrinkResponse(
    val idDrink: String = "",
    val strDrink: String = "",
    val strDrinkThumb: String = "",
)
