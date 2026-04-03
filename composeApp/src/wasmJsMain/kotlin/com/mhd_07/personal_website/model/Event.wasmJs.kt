package com.mhd_07.personal_website.model

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

@OptIn(ExperimentalWasmJsInterop::class)
actual suspend fun fetchEvents(): List<Event> {
    val response: Response = window.fetch(
        "https://raw.githubusercontent.com/MohammedHazemDawood/mohammedhazemdawood.github.io/main/_data/events.json"
    ).await()

    if (!response.ok) {
        error("Failed to fetch events - HTTP ${response.status}")
    }

    val jsonText: String = response.text().await<JsString>().toString()
//        .also { println("demo:$it") }   // keep your debug print

    println(jsonText)
    val eventsList = jsonFormat.decodeFromString<List<Event>>(jsonText)

    return eventsList
}