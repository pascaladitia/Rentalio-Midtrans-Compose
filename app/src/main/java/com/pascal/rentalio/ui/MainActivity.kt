package com.pascal.rentalio.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pascal.rentalio.ui.navigation.RouteScreen
import com.pascal.rentalio.ui.theme.RentalioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentalioTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                RouteScreen()
            }
        }
    }
}