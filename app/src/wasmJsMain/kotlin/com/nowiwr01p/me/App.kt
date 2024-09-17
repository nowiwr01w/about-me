package com.nowiwr01p.me

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nowiwr01p.me.core_ui.components.AppImage
import com.nowiwr01p.me.core_ui.theme.AppTypography
import com.nowiwr01p.me.core_ui.theme.colorAccent
import com.nowiwr01p.me.core_ui.theme.colorBackground
import com.nowiwr01p.me.core_ui.theme.colorText
import com.nowiwr01p.me.resources.Res
import com.nowiwr01p.me.resources.avatar

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
            .fillMaxWidth(0.6f)
            .fillMaxHeight()
    ) {
        HeaderWithPhotoAndContacts()
        Divider()
    }
}

@Composable
private fun HeaderWithPhotoAndContacts() {
    ContactsRow()
    Text(
        text = "Andrey Larionov | Android Developer",
        color = colorAccent,
        style = AppTypography.h1,
        modifier = Modifier.padding(top = 24.dp)
    )
}

@Composable
private fun ContactsRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        ContactItem(
            text = "EMAIL"
        )
        ContactItem(
            text = "LINKEDIN"
        )
        ContactItem(
            text = "MEDIUM"
        )
        ContactItem(
            text = "LEETCODE"
        )
        ContactItem(
            text = "TELEGRAM"
        )
        ContactItem(
            text = "WHATSAPP"
        )
    }
}

@Composable
private fun ContactItem(
    text: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {  }
    ) {
        Text(
            text = text,
            color = colorText,
            style = AppTypography.h3,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 18.dp)
        )
    }
}

/**
 * DIVIDER
 */
@Composable
private fun Divider() = Box(
    modifier = Modifier
        .padding(top = 32.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .height(1.dp)
        .background(colorText.copy(alpha = 0.2f))
)