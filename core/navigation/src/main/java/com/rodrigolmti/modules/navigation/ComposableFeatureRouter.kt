package com.rodrigolmti.modules.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface ComposableFeatureRouter : FeatureRouter {

    fun NavGraphBuilder.composable(navController: NavHostController) {
        composable(featureRoute, arguments, deepLinks) { backStackEntry ->
            Composable(navController, backStackEntry)
        }
    }

    @Composable
    fun Composable(
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    )
}