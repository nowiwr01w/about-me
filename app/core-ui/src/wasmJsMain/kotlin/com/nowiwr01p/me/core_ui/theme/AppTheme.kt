package com.nowiwr01p.me.core_ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.nowiwr01p.me.core_ui.theme.params.AppShapes
import com.nowiwr01p.me.core_ui.theme.params.AppTypography

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}