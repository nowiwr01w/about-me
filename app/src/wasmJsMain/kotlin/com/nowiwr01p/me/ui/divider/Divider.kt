package com.nowiwr01p.me.ui.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.colorText

@Composable
internal fun Divider() = Box(
    modifier = Modifier
        .padding(vertical = 32.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .height(1.dp)
        .background(colorText.copy(alpha = 0.2f))
)