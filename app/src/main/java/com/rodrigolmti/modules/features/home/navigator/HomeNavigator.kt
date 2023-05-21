package com.rodrigolmti.modules.features.home.navigator

import androidx.navigation.NavHostController
import com.rodrigolmti.drink.detail.entry.IDrinkDetailFeatureEntry
import com.rodrigolmti.modules.home.ui.home.IHomeDelegate

class HomeNavigator(
    private val navController: NavHostController,
    private val drinkDetailFeatureEntry: IDrinkDetailFeatureEntry,
) : IHomeDelegate {

    override fun onDrinkSelected(id: String) {
        navController.navigate(
            drinkDetailFeatureEntry
                .destination(id)
        )
    }
}