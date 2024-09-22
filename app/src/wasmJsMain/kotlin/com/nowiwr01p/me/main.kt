package com.nowiwr01p.me

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.nowiwr01p.me.core_ui.theme.AppTheme
import com.nowiwr01p.me.data.di.moduleData
import com.nowiwr01p.me.di.appModules
import com.nowiwr01p.me.ui.HomeMainScreen
import kotlinx.browser.document
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        setupKoin()
        AppTheme {
            HomeMainScreen()
        }
    }
}

@Composable
private fun setupKoin() = startKoin {
    modules(appModules)
}