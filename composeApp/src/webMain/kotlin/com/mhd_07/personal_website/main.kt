package com.mhd_07.personal_website

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.github.panpf.sketch.PlatformContext
import com.github.panpf.sketch.SingletonSketch
import com.github.panpf.sketch.Sketch
import com.github.panpf.sketch.decode.SvgDecoder

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        ScreenController()
    }
}