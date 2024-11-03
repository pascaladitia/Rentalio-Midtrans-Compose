package com.pascal.rentalio.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val navigationItems = listOf(
                NavigationItem(
                    title = "Home",
                    icon = Icons.Outlined.Home,
                    screen = Screen.HomeScreen
                ),
                NavigationItem(
                    title = "History",
                    icon = Icons.Outlined.AccessTime,
                    screen = Screen.HistoryScreen
                ),
                NavigationItem(
                    title = "Help",
                    icon = Icons.AutoMirrored.Outlined.Help,
                    screen = Screen.HelpScreen
                ),
                NavigationItem(
                    title = "Account",
                    icon = Icons.Outlined.SupervisorAccount,
                    screen = Screen.AccountScreen
                )
            )
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        val iconSize = if (currentRoute == item.screen.route) 32.dp else 28.dp
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(iconSize),
                            tint = if (currentRoute == item.screen.route) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    },
                    label = {
                        if (currentRoute == item.screen.route) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 10.sp
                                ),
                                color = Color.White
                            )
                        }

                    },
                    alwaysShowLabel = false,
                    selected = currentRoute == item.screen.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Transparent,
                        indicatorColor = Color.Transparent
                    ),
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
