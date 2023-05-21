package com.rodrigolmti.drink.detail.entry

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rodrigolmti.drink.detail.ui.DrinkDetailScreen


class DrinkDetailFeatureEntry : IDrinkDetailFeatureEntry() {

    override fun NavGraphBuilder.navigation(
        navController: NavHostController
    ) {
        navigation(startDestination = featureRoute, route = "@/drink-detail") {

            composable(route = featureRoute, arguments) {
                val drinkId = it.arguments?.getString(ARG_DRINK_ID) ?: ""

                DrinkDetailScreen(drinkId = drinkId) {
                    navController.popBackStack()
                }
            }
        }
    }
}