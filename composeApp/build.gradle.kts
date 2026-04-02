import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
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
            implementation("com.github.skydoves:landscapist-image:2.9.5")
// Optional plugins
            implementation("com.github.skydoves:landscapist-placeholder:2.9.5")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


