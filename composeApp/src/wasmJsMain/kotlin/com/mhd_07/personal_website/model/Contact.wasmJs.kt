package com.mhd_07.personal_website.model

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

val jsonFormat = Json {
    ignoreUnknownKeys = true
    isLenient = true
    coerceInputValues = true
}

@OptIn(ExperimentalWasmJsInterop::class)
actual suspend fun fetchContacts(): List<Contact> {
    val response: Response = window.fetch(
        "https://raw.githubusercontent.com/MohammedHazemDawood/MohammedHazemDawood/main/_data/contact.json"
    ).await()

    if (!response.ok) {
        error("Failed to fetch contacts - HTTP ${response.status}")
    }

    val jsonText: String = response.text().await<JsString>().toString()

    return jsonFormat.decodeFromString(jsonText)

}