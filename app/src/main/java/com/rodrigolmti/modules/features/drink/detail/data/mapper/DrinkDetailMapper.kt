package com.rodrigolmti.modules.features.drink.detail.data.mapper

import com.rodrigolmti.drink.detail.domain.model.DrinkDetail
import com.rodrigolmti.modules.features.drink.detail.data.response.BodyDetailDrinkResponse

fun BodyDetailDrinkResponse.toModel(): DrinkDetail? {
    if (drinks.isNotEmpty()) {
        val item = drinks.first()

        return DrinkDetail(
            strInstructions = item.strInstructions ?: "",
            strDrinkThumb = item.strDrinkThumb,
            strDrink = item.strDrink,
            strAlcoholic = item.strAlcoholic,
            strGlass = item.strGlass ?: "",
            ingredients = listOf(
                item.strIngredient1 ?: "",
                item.strIngredient2 ?: "",
                item.strIngredient3 ?: "",
                item.strIngredient4 ?: "",
                item.strIngredient5 ?: "",
                item.strIngredient6 ?: "",
                item.strIngredient7 ?: "",
                item.strIngredient8 ?: "",
                item.strIngredient9 ?: "",
                item.strIngredient10 ?: "",
                item.strIngredient11 ?: "",
                item.strIngredient12 ?: "",
                item.strIngredient13 ?: "",
                item.strIngredient14 ?: "",
                item.strIngredient1 ?: "",
            ).filter { it.isNotEmpty() },
            measures = listOf(
                item.strMeasure1 ?: "",
                item.strMeasure2 ?: "",
                item.strMeasure3 ?: "",
                item.strMeasure4 ?: "",
                item.strMeasure5 ?: "",
                item.strMeasure6 ?: "",
                item.strMeasure7 ?: "",
                item.strMeasure8 ?: "",
                item.strMeasure9 ?: "",
                item.strMeasure10 ?: "",
                item.strMeasure11 ?: "",
                item.strMeasure12 ?: "",
                item.strMeasure13 ?: "",
                item.strMeasure14 ?: "",
                item.strMeasure1 ?: "",
            ).filter { it.isNotEmpty() }
        )
    }

    return null
}