package com.pascal.rentalio.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pascal.rentalio.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(48.dp)
            .clickable { onRetry() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}