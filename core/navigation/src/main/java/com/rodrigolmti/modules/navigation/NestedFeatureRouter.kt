package com.rodrigolmti.modules.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NestedFeatureRouter : FeatureRouter {

    fun NavGraphBuilder.navigation(navController: NavHostController)
}