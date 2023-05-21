package com.rodrigolmti.drink.detail.domain.model

data class DrinkDetail(
    val strDrink: String,
    val strDrinkThumb: String,
    val strInstructions: String,
    val strGlass: String,
    val ingredients: List<String>,
    val measures: List<String>,
    val strAlcoholic: String,
)