import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.3.20"
}

compose.resources {          // ← here, top level
    publicResClass = true
    generateResClass = always
}


kotlin {
//    js {
//        browser()
//        binaries.executable()
//    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }



    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("tech.annexflow.compose:constraintlayout-compose-multiplatform:0.7.0")
            implementation("io.github.panpf.sketch4:sketch-compose:4.4.0-beta02")
            implementation("io.github.panpf.sketch4:sketch-http:4.4.0-beta02")
            implementation("io.github.panpf.sketch4:sketch-svg:4.4.0-beta02")
            implementation("io.github.panpf.sketch4:sketch-http-ktor3:4.4.0-beta02")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


