package com.a84a.quizzy.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object  Notification : Screen("notification")
}
