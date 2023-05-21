package com.rodrigolmti.modules.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigolmti.modules.home.ui.home.HomeScreen
import com.rodrigolmti.modules.ui_kit.AppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {

                val navController = rememberNavController()

                Scaffold {
                    NavigationGraph(
                        navController = navController,
                    )
                }
            }
        }

        reportFullyDrawn()
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "/home") {
        composable("/home") {
            HomeScreen()
        }
    }
}