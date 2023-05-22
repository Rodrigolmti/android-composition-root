package com.rodrigolmti.drink.detail.entry

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rodrigolmti.modules.navigation.ComposableFeatureRouter

abstract class IDrinkDetailFeatureRouter : ComposableFeatureRouter {

    final override val featureRoute = "/drink-detail/{$ARG_DRINK_ID}"

    final override val arguments = listOf(
        navArgument(ARG_DRINK_ID) {
            type = NavType.StringType
        }
    )

    fun destination(movieId: String): String =
        "/drink-detail/$movieId"

    protected companion object {
        const val ARG_DRINK_ID = "id"
    }
}