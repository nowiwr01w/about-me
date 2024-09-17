package com.nowiwr01p.me.ui.header

import org.jetbrains.compose.resources.StringResource

data class Contact(
    val name: StringResource,
    val onContactClick: () -> Unit
)