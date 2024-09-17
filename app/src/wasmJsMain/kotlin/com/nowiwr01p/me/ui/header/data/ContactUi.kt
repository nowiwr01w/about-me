package com.nowiwr01p.me.ui.header.data

import org.jetbrains.compose.resources.StringResource

data class ContactUi(
    val name: StringResource,
    val redirectUrl: String,
    val onContactClick: () -> Unit
)