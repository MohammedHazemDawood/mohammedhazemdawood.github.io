package com.mhd_07.personal_website.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val title: String,
    val link: String,
    val icon: String
)

expect suspend fun fetchContacts(): List<Contact>
