package com.nowiwr01p.me

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.AppTypography
import com.nowiwr01p.me.core_ui.theme.colorAccent
import com.nowiwr01p.me.core_ui.theme.colorBackground
import com.nowiwr01p.me.core_ui.theme.colorText

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

@Composable
private fun HeaderWithPhotoAndContacts() {
    ContactsRow()
    Text(
        text = "Andrey Larionov | Android Developer",
        color = colorAccent,
        style = AppTypography.h1
    )
}

@Composable
private fun ContactsRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 14.dp)
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
            style = AppTypography.h4,
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
        .padding(vertical = 32.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .height(1.dp)
        .background(colorText.copy(alpha = 0.2f))
)