package com.mhd_07.personal_website.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.g_bold
import personalwebsite.composeapp.generated.resources.g_extra_bold
import personalwebsite.composeapp.generated.resources.g_extra_light
import personalwebsite.composeapp.generated.resources.g_light
import personalwebsite.composeapp.generated.resources.g_medium
import personalwebsite.composeapp.generated.resources.g_regular
import personalwebsite.composeapp.generated.resources.g_semi_bold
import personalwebsite.composeapp.generated.resources.g_thin

val AppFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.g_thin,       FontWeight.Thin),
        Font(Res.font.g_extra_light,FontWeight.ExtraLight),
        Font(Res.font.g_light,      FontWeight.Light),
        Font(Res.font.g_regular,    FontWeight.Normal),
        Font(Res.font.g_medium,     FontWeight.Medium),
        Font(Res.font.g_semi_bold,  FontWeight.SemiBold),
        Font(Res.font.g_bold,       FontWeight.Bold),
        Font(Res.font.g_extra_bold, FontWeight.ExtraBold),
    )

data class Typography(
    val display: TextStyle,
    val titleLarge: TextStyle,
    val titleSmall: TextStyle,
    val description: TextStyle,
)

object AppTypography {
    @Composable
    fun get(sizes: FontSizes): Typography {
        val fontFamily = AppFontFamily   // ← this is where the magic happens

        return Typography(
            display = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = sizes.display.value,
            ),
            titleLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = sizes.titleLarge.value,
            ),
            titleSmall = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = sizes.titleSmall.value,
            ),
            description = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraLight,
                fontSize = sizes.description.value,
            )
        )
    }
}

