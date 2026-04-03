package com.mhd_07.personal_website.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

object Breakpoints {
    const val MOBILE_MAX = 767
    const val TABLET_MAX = 1024
    const val DESKTOP_MAX = 1440
}

enum class ScreenType {
    MOBILE,
    DESKTOP
}

data class ScreenInfo(
    val width: Int,
    val height: Int,
    val type: ScreenType = when {
        width <= Breakpoints.TABLET_MAX -> ScreenType.MOBILE
        else -> ScreenType.DESKTOP
    }
)

@Composable
expect fun rememberScreenInfo() : State<ScreenInfo>
