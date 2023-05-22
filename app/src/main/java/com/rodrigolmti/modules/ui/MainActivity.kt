package com.rodrigolmti.modules.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rodrigolmti.drink.detail.entry.IDrinkDetailFeatureRouter
import com.rodrigolmti.modules.home.entry.IHomeFeatureRouter
import com.rodrigolmti.modules.ui_kit.AppTheme
import com.rodrigolmti.modules.ui_kit.DarkGreenGray10
import com.rodrigolmti.modules.ui_kit.NavigationBar
import com.rodrigolmti.modules.ui_kit.StatusBar
import org.koin.java.KoinJavaComponent.getKoin

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                StatusBar(window, color = MaterialTheme.colors.background)

                Surface(color = MaterialTheme.colors.background) {
                    NavigationGraph()
                }

                NavigationBar(window, color = DarkGreenGray10)
            }
        }

        reportFullyDrawn()
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    val homeEntry = getKoin().get<IHomeFeatureRouter>()
    val drinkDetailEntry = getKoin().get<IDrinkDetailFeatureRouter>()

    NavHost(navController, startDestination = homeEntry.featureRoute) {
        with(homeEntry) {
            composable(navController)
        }
        with(drinkDetailEntry) {
            composable(navController)
        }
    }
}
