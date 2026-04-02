package com.mhd_07.personal_website.util

import kotlinx.browser.window

actual class ScreenSizeHandler  actual constructor(){
    private var currentScreenInfo = getCurrentScreenInfo()
    private var listeners = mutableListOf<(ScreenInfo) -> Unit>()

    init {
        window.addEventListener("resize", {
            val newInfo = getCurrentScreenInfo()
            if (newInfo.type != currentScreenInfo.type) {
                currentScreenInfo = newInfo
                notifyAllListeners()
            }
        })
    }

    private fun getCurrentScreenInfo(): ScreenInfo {
        val width = window.innerWidth
        val height = window.innerHeight
        return ScreenInfo(width, height)
    }

    private fun notifyAllListeners() = listeners.forEach { it(currentScreenInfo) }

    actual fun getScreenInfo() = currentScreenInfo

    actual fun addListener(listener: (ScreenInfo) -> Unit) {
        listeners.add(listener)
    }

    actual fun removeListener(listener: (ScreenInfo) -> Unit) {
        listeners.remove(listener)
    }

}