package com.nowiwr01p.me

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.colorBackground
import com.nowiwr01p.me.ui.divider.Divider
import com.nowiwr01p.me.ui.header.HeaderWithPhotoAndContacts

@Composable
internal fun AboutMeContent() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
    ) {
        Content()
    }
}

@Composable
private fun Content() {
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