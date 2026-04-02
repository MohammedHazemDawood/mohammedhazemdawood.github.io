package com.mhd_07.personal_website.model

data class Event(
    val title : String,
    val description : String,
    val myRole : String,
    val expandedRole : String,
    val takeaways : String,
    val location : String,
    val images : List<String>,
    val date : String,
)