package com.mhd_07.personal_website.model

import kotlinx.serialization.Serializable


@Serializable
data class Project(
    val title: String = "",
    val description: String = "",
    val longDescription: String = "",
    val techStack: List<String> = emptyList(),
    val images: List<String> = emptyList(),
    val githubUrl: String = "",
    val liveUrl: String = "",
    val date: String = "",
    val status: String = "",        // "completed", "in progress", "archived"
)

@Serializable
data class ProjectResponse(
    val title: String= "",
    val description: String= "",
    val longDescription: String= "",
    val techStack: String= "",
    val images: List<String> = emptyList(),
    val githubUrl: String= "",
    val liveUrl: String= "",
    val date: String= "",
    val status: String= "",        // "completed", "in progress", "archived"
)

expect suspend fun fetchProjects(): List<Project>
