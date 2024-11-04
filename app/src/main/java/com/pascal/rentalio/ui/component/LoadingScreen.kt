package com.pascal.rentalio.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.pascal.rentalio.R
import com.pascal.rentalio.ui.theme.RentalioTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.loading1)
            .decoderFactory(GifDecoder.Factory())
            .crossfade(true)
            .build()

        Column(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(24.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(100.dp)
            )
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                model = imageRequest,
                contentDescription = "GIF"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    RentalioTheme {
        LoadingScreen()
    }
}