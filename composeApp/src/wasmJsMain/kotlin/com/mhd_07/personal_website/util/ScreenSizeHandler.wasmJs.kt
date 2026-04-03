package com.mhd_07.personal_website.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.browser.window
import org.w3c.dom.events.Event

@Composable
actual fun rememberScreenInfo(): State<ScreenInfo> {
    val screenInfo = remember { mutableStateOf(calculateScreenInfo()) }

    DisposableEffect(Unit) {
        window.requestAnimationFrame {
            screenInfo.value = calculateScreenInfo()
        }
        val listener: (Event) -> Unit = {
            screenInfo.value = calculateScreenInfo()
        }
        window.addEventListener("resize", listener)

        onDispose {
            window.removeEventListener("resize", listener)
        }
    }
    return screenInfo
}

fun calculateScreenInfo(): ScreenInfo {
    val width = window.innerWidth
    val height = window.innerHeight

    val agent = window.navigator.userAgent

    val isMob = listOf(
        "mobi",
        "android",
        "iphone",
        "ipad",
        "ipod",
        "blackberry",
        "windows phone"
    ).any { agent.contains(it) }

    return when {
        (isMob && width <= Breakpoints.MOBILE_MAX) -> {
            ScreenInfo(width, height, ScreenType.MOBILE)
        }

        (width <= Breakpoints.TABLET_MAX) -> {
            ScreenInfo(
                width,
                height,
                ScreenType.MOBILE/* there is no difference between mobile and tablet for my ui */
            )
        }

        else -> ScreenInfo(width, height, if (isMob) ScreenType.MOBILE else ScreenType.DESKTOP)
    }
}