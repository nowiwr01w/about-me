import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    applyDefaultHierarchyTemplate()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {

        }
    }

    sourceSets {
        val wasmJsMain by getting {
            dependencies {
                /**
                 * PROJECT MODULES
                 */
                implementation(projects.shared)
                implementation(projects.app.data)
                implementation(projects.app.domain)
                /**
                 * COMPOSE
                 */
                implementation(compose.runtime)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.koin.compose)
            }
        }
    }
}