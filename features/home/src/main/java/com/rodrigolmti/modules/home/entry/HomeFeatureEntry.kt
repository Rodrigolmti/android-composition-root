package com.rodrigolmti.modules.home.entry

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.rodrigolmti.modules.home.ui.home.HomeScreen
import com.rodrigolmti.modules.home.ui.home.IHomeDelegate
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class HomeFeatureEntry : IHomeFeatureEntry() {

    @Composable
    override fun Composable(
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {
        val delegate = koinInject<IHomeDelegate> { parametersOf(navController) }

        HomeScreen(delegate = delegate)
    }
}