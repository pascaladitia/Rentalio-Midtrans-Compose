package com.pascal.rentalio.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pascal.rentalio.ui.screen.account.AccountScreen
import com.pascal.rentalio.ui.screen.detail.DetailScreen
import com.pascal.rentalio.ui.screen.help.HelpScreen
import com.pascal.rentalio.ui.screen.history.HistoryScreen
import com.pascal.rentalio.ui.screen.home.HomeScreen
import com.pascal.rentalio.ui.screen.payment.PaymentScreen
import com.pascal.rentalio.ui.screen.splash.SplashScreen

@Composable
fun RouteScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.HomeScreen.route,
                    Screen.HistoryScreen.route,
                    Screen.HelpScreen.route,
                    Screen.AccountScreen.route
            )) {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(700))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(700))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(700))
            }
        ) {
            composable(route = Screen.SplashScreen.route) {
                SplashScreen(
                    paddingValues = paddingValues
                ) {
                    navController.navigate( Screen.HomeScreen.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(
                    paddingValues = paddingValues,
                    onDetail = {
                        navController.popBackStack()
                        navController.navigate(Screen.DetailScreen.route)
                    }
                )
            }
            composable(route = Screen.DetailScreen.route) {
                DetailScreen(
                    paddingValues = paddingValues,
                    onPayment = {
                        navController.navigate(Screen.PaymentScreen.route)
                    },
                    onNavBack = {
                        navController.popBackStack()
                        navController.navigate(Screen.HomeScreen.route)
                    }
                )
            }
            composable(route = Screen.HistoryScreen.route) {
                HistoryScreen(
                    paddingValues = paddingValues
                )
            }
            composable(route = Screen.HelpScreen.route) {
                HelpScreen(
                    paddingValues = paddingValues
                )
            }
            composable(route = Screen.AccountScreen.route) {
                AccountScreen(
                    paddingValues = paddingValues
                )
            }
            composable(route = Screen.PaymentScreen.route) {
                PaymentScreen(
                    paddingValues = paddingValues
                )
            }
        }
    }
}
