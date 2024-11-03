package com.pascal.rentalio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pascal.rentalio.R

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
)