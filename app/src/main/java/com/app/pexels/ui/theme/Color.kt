package com.app.pexels.ui.theme

import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Dark = Color(0xFF1E1E1E)
val LightGray = Color(0xFFF3F5F9)
val DarkGray = Color(0xFF393939)
val DarkRed = Color(0xFFBB1020)
val HintGray = Color(0xFF868686)
val HintLight = Color(0xFFB5B5B5)
val LightYellow = Color(0xFFFFCC8F)
val LightOrange = Color(0xFFFFAF51)

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val inversePrimary: Color,
    val textHint: Color,
    val progressBar: Color,
) {
    object Night : ThemeColors(
        background = Dark,
        surface = DarkGray,
        primary = DarkRed,
        inversePrimary = White,
        textHint = HintLight,
        progressBar = HintLight,
    )

    object Day : ThemeColors(
        background = White,
        surface = LightGray,
        primary = DarkRed,
        inversePrimary = Dark,
        textHint = HintGray,
        progressBar = LightGray,
    )
}