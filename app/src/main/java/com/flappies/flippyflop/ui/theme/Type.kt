package com.flappies.flippyflop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.flappies.flippyflop.R

// Set of Material typography styles to start with
val RubikSprayPaint = FontFamily(
    Font(R.font.rubik_spray_paint, FontWeight.Normal)
)

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = RubikSprayPaint,
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp,
        lineHeight = 60.sp,
        letterSpacing = 0.5.sp,
        color = SoftRed,
        textAlign = TextAlign.Center
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp,
        color = Green
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)