package com.mhd_07.personal_website

import kotlinx.browser.window

actual fun openUrl(url: String) {
    window.location.href = url
}