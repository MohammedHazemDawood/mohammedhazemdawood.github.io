package com.mhd_07.personal_website.model

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

@OptIn(ExperimentalWasmJsInterop::class)
actual suspend fun fetchProjects(): List<Project> {
    val response: Response = window.fetch(
        "https://raw.githubusercontent.com/MohammedHazemDawood/mohammedhazemdawood.github.io/main/_data/projects.json"
    ).await()

    if (!response.ok) {
        error("Failed to fetch projects - HTTP ${response.status}")
    }

    val jsonText: String = response.text().await<JsString>().toString()

    return if (jsonText.isEmpty()) emptyList() else jsonFormat.decodeFromString<List<ProjectResponse>>(jsonText).map { responseItem ->
        Project(
            title = responseItem.title,
            description = responseItem.description,
            longDescription = responseItem.longDescription,
            techStack = responseItem.techStack.split(",").map { it.trim() },
            images = responseItem.images,
            githubUrl = responseItem.githubUrl,
            liveUrl = responseItem.liveUrl,
            date = responseItem.date,
            status = responseItem.status
        )
    }

}