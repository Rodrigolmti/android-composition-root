package com.rodrigolmti.modules.features.home.navigator

import androidx.navigation.NavHostController
import com.rodrigolmti.modules.home.ui.home.IHomeDelegate

class HomeNavigator(private val navController: NavHostController) : IHomeDelegate {

    override fun onDrinkSelected(id: String) = navController.navigate("/drink-detail/$id")
}