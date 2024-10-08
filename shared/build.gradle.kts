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
                implementation(projects.resources)
                /**
                 * COMPOSE
                 */
                implementation(compose.runtime)
                implementation(compose.components.resources)
                /**
                 * DEPENDENCIES
                 */
                implementation(libs.kotlin.date.time)
            }
        }
    }
}