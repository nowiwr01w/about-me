package com.nowiwr01p.me.ui.data.tech_stack

import com.nowiwr01p.me.ui.data.tech_stack.TechStackData.*

sealed class TechStackData(
    val category: String,
    val skills: String
) {
    data object KMM : TechStackData(
        category = "KMM",
        skills = "Android, iOS, Desktop, Web with Wasm, Ktor server"
    )
    data object Testing : TechStackData(
        category = "Testing",
        skills = "JUnit, Mockito, Espresso, Kaspresso"
    )
    data object Workflow : TechStackData(
        category = "Workflow",
        skills = "Git, Jira, YouTrack, ClickUp, Confluence, Azure, Gitlab, Zeplin, Figma, Slack"
    )
    data object Stack : TechStackData(
        category = "Stack",
        skills = "Kotlin, Jetpack Compose, Coroutines + Flow, Retrofit2, MVI/MVVM, Clean Architecture, Koin, Room, DataStore, Firebase, WorkManager, Gitlab CI/CD, Github Actions"
    )
}

internal val techProficiences = listOf(
    KMM,
    Testing,
    Workflow,
    Stack
)