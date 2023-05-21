package com.rodrigolmti.modules.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rodrigolmti.drink.detail.entry.IDrinkDetailFeatureEntry
import com.rodrigolmti.modules.home.entry.IHomeFeatureEntry
import com.rodrigolmti.modules.navigation.FeatureEntry
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

    val homeEntry = getKoin().get<IHomeFeatureEntry>()
    val drinkDetailEntry = getKoin().get<IDrinkDetailFeatureEntry>()

    NavHost(navController, startDestination = homeEntry.featureRoute) {
        with(homeEntry) {
            composable(navController)
        }
        with(drinkDetailEntry) {
            navigation(navController)
        }
    }
}
