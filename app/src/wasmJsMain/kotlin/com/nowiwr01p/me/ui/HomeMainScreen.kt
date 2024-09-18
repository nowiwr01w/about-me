package com.nowiwr01p.me.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.colorBackground
import com.nowiwr01p.me.ui.HomeContract.*
import com.nowiwr01p.me.ui.divider.Divider
import com.nowiwr01p.me.ui.header.HeaderWithPhotoAndContacts
import org.jetbrains.compose.ui.tooling.preview.Preview
import rememberViewModel

@Composable
internal fun HomeMainScreen(
    viewModel: HomeViewModel = rememberViewModel()
) {
    val listener = object : Listener {

    }

    LaunchedEffect(Unit) {
//        viewModel.setEvent(Event.Init)
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
    ) {
        Content(
            state = State(),
            listener = listener
        )
    }
}

@Composable
private fun Content(
    state: State,
    listener: Listener?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .widthIn(max = 900.dp)
            .fillMaxHeight()
    ) {
        HeaderWithPhotoAndContacts()
        Divider()
    }
}

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = Content(
    state = State(),
    listener = null
)