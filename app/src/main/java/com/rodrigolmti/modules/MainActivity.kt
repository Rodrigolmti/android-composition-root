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
import com.rodrigolmti.modules.home.ui.BottomNavItem
import com.rodrigolmti.modules.home.ui.CustomBottomNavigation
import com.rodrigolmti.modules.home.ui.HomeScreen
import com.rodrigolmti.modules.orders.OrdersScreen
import com.rodrigolmti.modules.profile.ProfileScreen
import com.rodrigolmti.modules.ui_kit.ComposeTheme
import com.rodrigolmti.modules.ui_kit.R

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {

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

class HomeNavItem : BottomNavItem {
    override val screenRoute: String = "home"
    override val icon: Int = R.drawable.ic_home
    override val title: String = "Home"
}

class OrdersNavItem : BottomNavItem {
    override val screenRoute: String = "orders"
    override val icon: Int = R.drawable.ic_order
    override val title: String = "Orders"
}

class ProfileNavItem : BottomNavItem {
    override val screenRoute: String = "profile"
    override val icon: Int = R.drawable.ic_profile
    override val title: String = "Profile"
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