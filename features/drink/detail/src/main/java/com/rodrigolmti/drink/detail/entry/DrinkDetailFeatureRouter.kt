package com.rodrigolmti.drink.detail.entry

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.rodrigolmti.drink.detail.ui.DrinkDetailScreen

class DrinkDetailFeatureRouter : IDrinkDetailFeatureRouter() {

    @Composable
    override fun Composable(
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {
        val drinkId = backStackEntry.arguments?.getString(ARG_DRINK_ID) ?: ""

        DrinkDetailScreen(drinkId = drinkId) {
            navController.popBackStack()
        }
    }
}