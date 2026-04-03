package com.mhd_07.personal_website

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mhd_07.personal_website.theme.Theme
import com.mhd_07.personal_website.theme.Themes
import com.mhd_07.personal_website.util.ScreenType
import com.mhd_07.personal_website.util.rememberScreenInfo

val LocalTheme = compositionLocalOf<Theme> { error("No theme provided!") }

@Composable
fun ScreenController() {
    val mobileTheme = Themes.Mobile()
    val desktopTheme = Themes.Desktop()
    val screenType by rememberScreenInfo()
    val currentTheme by remember { derivedStateOf { if (screenType.type == ScreenType.MOBILE) mobileTheme else desktopTheme } }

    CompositionLocalProvider(
        LocalTheme provides currentTheme,
    ) {
        SelectionContainer {
            HomeScreen()
        }
    }
}

expect fun openUrl(url: String)