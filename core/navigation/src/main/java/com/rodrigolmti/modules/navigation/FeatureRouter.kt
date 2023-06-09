package com.rodrigolmti.modules.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

interface FeatureRouter {

    val featureRoute: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}

