package com.pascal.rentalio.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pascal.rentalio.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    onTimeout: (Boolean) -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(500)
        onTimeout(true)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(120.dp)
        )
    }
}

