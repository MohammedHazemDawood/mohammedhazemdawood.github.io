package com.mhd_07.personal_website.theme

import androidx.compose.runtime.Composable
import com.mhd_07.personal_website.util.ScreenType

data class Theme(
    val colors: Colors,
    val dimensions: Dimensions,
    val fontSizes: FontSizes,
    val typography: Typography,
    val screenType: ScreenType
)

object Themes {

    @Composable
    fun Desktop() = Theme(
        colors = Colors,
        dimensions = Dimensions.Desktop,
        fontSizes = FontSizes.Desktop,
        typography = AppTypography.get(FontSizes.Desktop),
        screenType = ScreenType.DESKTOP
    )

    @Composable
    fun Mobile() = Theme(
        colors = Colors,
        dimensions = Dimensions.Mobile,
        fontSizes = FontSizes.Mobile,
        typography = AppTypography.get(FontSizes.Mobile),
        screenType = ScreenType.MOBILE
    )
}