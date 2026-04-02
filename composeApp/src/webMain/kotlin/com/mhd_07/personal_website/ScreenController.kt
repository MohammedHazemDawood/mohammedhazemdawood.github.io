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
import com.mhd_07.personal_website.util.ScreenInfo
import com.mhd_07.personal_website.util.ScreenSizeHandler
import com.mhd_07.personal_website.util.ScreenType

val LocalTheme = compositionLocalOf<Theme> { error("No theme provided!") }

@Composable
fun ScreenController() {
    val screenSizeHandler = ScreenSizeHandler()
    val mobileTheme = Themes.Mobile()
    val desktopTheme = Themes.Desktop()
    var screenType by remember { mutableStateOf(ScreenType.DESKTOP) }
    val currentTheme by remember { derivedStateOf { if (screenType == ScreenType.MOBILE) mobileTheme else desktopTheme } }

    DisposableEffect(Unit) {
        val listener: (ScreenInfo) -> Unit = { screenInfo ->
            screenType = screenInfo.type
        }
        screenSizeHandler.addListener(listener)
        onDispose {
            screenSizeHandler.removeListener(listener) // cleanup on leave
        }
    }

    CompositionLocalProvider(
        LocalTheme provides currentTheme,
    ) {
        SelectionContainer {
                HomeScreen()
        }
    }
}

expect fun openUrl(url : String)