package com.mhd_07.personal_website.model

import kotlinx.serialization.Serializable

@Serializable
data class Certificate(
    val title: String= "",
    val issuer: String = "",
    val date: String = "",
    val image: String = "",
    val url: String = "",
)

expect suspend fun fetchCertificates(): List<Certificate>