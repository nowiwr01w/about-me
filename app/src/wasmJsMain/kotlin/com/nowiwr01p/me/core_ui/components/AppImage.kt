package com.nowiwr01p.me.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AppImage(
    image: DrawableResource,
    modifier: Modifier = Modifier,
    color: Color? = null,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = ""
) {
    Image(
        painter = painterResource(image),
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = if (color != null) ColorFilter.tint(color) else null,
        modifier = modifier
    )
}