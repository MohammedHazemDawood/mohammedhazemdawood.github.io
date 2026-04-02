package com.mhd_07.personal_website.util

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

expect class ScreenSizeHandler constructor() {
    fun getScreenInfo() : ScreenInfo
    fun addListener(listener: (ScreenInfo) -> Unit)
    fun removeListener(listener: (ScreenInfo) -> Unit)
}