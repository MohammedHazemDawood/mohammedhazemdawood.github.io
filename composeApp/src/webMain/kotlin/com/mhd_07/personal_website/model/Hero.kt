package com.mhd_07.personal_website.model

import kotlinx.serialization.Serializable

@Serializable
data class Hero(
    val greeting: String = "",
    val summary: String = "",
    val image: String = "",
    val resume: String = ""
)

expect suspend fun fetchHero(): Hero