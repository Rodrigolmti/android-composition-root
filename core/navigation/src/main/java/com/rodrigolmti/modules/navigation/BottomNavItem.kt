package com.rodrigolmti.modules.navigation

import androidx.compose.runtime.Composable


interface NavigationRoute {
    val routes: Map<String, @Composable Function<*>>
}

