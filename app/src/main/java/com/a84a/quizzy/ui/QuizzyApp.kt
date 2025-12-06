package com.a84a.quizzy

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a84a.quizzy.ui.home.HomeScreen
import com.a84a.quizzy.ui.navigation.Screen
import com.a84a.quizzy.ui.notification.SettingsScreen

@Composable
fun QuizzyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // LOGIN
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // HOME
        composable(Screen.Home.route) {
            HomeScreen(
                onNotificationClick = {
                    navController.navigate(Screen.Notification.route)
                }
            )
        }

        // SETTINGS
        composable(Screen.Notification.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                onLogoutClick = {
                }
            )
        }
    }
}
