package com.pascal.rentalio.ui.screen.payment

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.rentalio.ui.theme.RentalioTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: PaymentViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.initPayment(context)
    }

    Surface(
        modifier = modifier.padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        PaymentContent()
    }
}

@Composable
fun PaymentContent(
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
private fun PaymentPreview() {
    RentalioTheme {
        PaymentContent()
    }
}