package com.rodrigolmti.modules.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface AggregateFeatureEntry : FeatureEntry {

    fun NavGraphBuilder.navigation(navController: NavHostController)
}

interface ComposableFeatureEntry : FeatureEntry {

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

@Composable
fun NavBackStackEntry.rememberBackStackEntry(
    navController: NavHostController,
    route: String
): NavBackStackEntry {
    return remember(this) { navController.getBackStackEntry(route) }
}