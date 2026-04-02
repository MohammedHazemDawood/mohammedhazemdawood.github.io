package com.mhd_07.personal_website.model

data class Project(
    val title: String,
    val description: String,
    val longDescription: String,
    val techStack: List<String>,
    val images: List<String>,
    val githubUrl: String,
    val liveUrl: String,
    val date: String,
    val status: String,        // "completed", "in progress", "archived"
)
