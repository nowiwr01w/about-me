package com.nowiwr01p.me.core_ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.nowiwr01p.me.resources.*
import org.jetbrains.compose.resources.Font

val JetBrainsMono @Composable get() = FontFamily(
    Font(Res.font.JetBrainsMono_Regular, FontWeight.Normal),
    Font(Res.font.JetBrainsMono_Bold, FontWeight.Bold),
    Font(Res.font.JetBrainsMono_ExtraBold, FontWeight.ExtraBold),
    Font(Res.font.JetBrainsMono_Medium, FontWeight.Medium),
    Font(Res.font.JetBrainsMono_SemiBold, FontWeight.SemiBold),
)