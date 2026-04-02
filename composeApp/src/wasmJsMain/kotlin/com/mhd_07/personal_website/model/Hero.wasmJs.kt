package com.mhd_07.personal_website.model

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.Response

@OptIn(ExperimentalWasmJsInterop::class)
actual suspend fun fetchHero(): Hero {
    val response: Response =
        window.fetch("https://raw.githubusercontent.com/MohammedHazemDawood/MohammedHazemDawood/main/_data/hero.json")
            .await()

    val json = response.text().await<JsString>().toString()

    return jsonFormat.decodeFromString<Hero>(json)
}