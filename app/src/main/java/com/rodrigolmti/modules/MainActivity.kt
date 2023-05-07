package com.rodrigolmti.modules

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigolmti.modules.bottom_navigation.HomeNavItem
import com.rodrigolmti.modules.bottom_navigation.OrdersNavItem
import com.rodrigolmti.modules.bottom_navigation.ProfileNavItem
import com.rodrigolmti.modules.home.ui.CustomBottomNavigation
import com.rodrigolmti.modules.home.ui.HomeScreen
import com.rodrigolmti.modules.navigation.BottomNavItem
import com.rodrigolmti.modules.orders.OrdersScreen
import com.rodrigolmti.modules.profile.ProfileScreen
import com.rodrigolmti.modules.ui_kit.AppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                val bottomNavigationItems = listOf(
                    HomeNavItem(),
                    OrdersNavItem(),
                    ProfileNavItem()
                )

                val navController = rememberNavController()

                Scaffold(bottomBar = {
                    BottomAppBar(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.BottomCenter)
                    ) {
                        CustomBottomNavigation(
                            navController = navController,
                            bottomNavigationItems,
                        )
                    }
                }) {
                    NavigationGraph(
                        navController = navController,
                        bottomNavigationItems,
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    destinations: List<BottomNavItem> = listOf(HomeNavItem())
) {
    NavHost(navController, startDestination = destinations.first().screenRoute) {
        destinations.map { destination ->
            composable(destination.screenRoute) {
                when (destination) {
                    is HomeNavItem -> HomeScreen()
                    is OrdersNavItem -> OrdersScreen()
                    is ProfileNavItem -> ProfileScreen()
                }
            }
        }
    }
}