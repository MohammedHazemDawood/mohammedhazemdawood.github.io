package com.mhd_07.personal_website.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val title : String = "",
    val description : String = "",
    val myRole : String = "",
    val expandedRole : String = "",
    val takeaways : String = "",
    val location : String = "",
    val images : List<String> = emptyList(),
    val date : String = "",
)

expect suspend fun fetchEvents() : List<Event>