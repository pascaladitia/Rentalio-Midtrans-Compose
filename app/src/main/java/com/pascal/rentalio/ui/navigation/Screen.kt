package com.pascal.rentalio.ui.navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen("splash")
    data object HomeScreen: Screen("home")
    data object DetailScreen: Screen("detail")
    data object HistoryScreen: Screen("history")
    data object HelpScreen: Screen("help")
    data object AccountScreen: Screen("account")
}