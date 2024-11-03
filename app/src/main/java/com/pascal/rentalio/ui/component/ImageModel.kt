package com.pascal.rentalio.ui.component

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil.request.ImageRequest
import com.pascal.rentalio.R

@Composable
fun ImageModel(
    context: Context,
    url: String? = ""
): ImageRequest {
    val model = remember {
        ImageRequest.Builder(context)
            .data(url)
            .size(1024)
            .crossfade(true)
            .placeholder(R.drawable.no_profile)
            .error(R.drawable.no_profile)
            .build()
    }
    return model
}