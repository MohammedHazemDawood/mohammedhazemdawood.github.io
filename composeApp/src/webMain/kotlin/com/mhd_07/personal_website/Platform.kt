package com.mhd_07.personal_website

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform