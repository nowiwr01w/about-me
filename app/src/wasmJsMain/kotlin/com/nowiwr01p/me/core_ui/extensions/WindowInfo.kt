package com.nowiwr01p.me.core_ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun getScreenWidth() = rememberUpdatedState(
    newValue = with(LocalDensity.current) {
        LocalWindowInfo.current.containerSize.width.dp / density
    }
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun getScreenHeight() = rememberUpdatedState(
    newValue = with(LocalDensity.current) {
        LocalWindowInfo.current.containerSize.height.dp / density
    }
)