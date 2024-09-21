package com.nowiwr01p.me.ui.header

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
import com.nowiwr01p.me.core_ui.theme.colorText
import com.nowiwr01p.me.shared.ContactData
import com.nowiwr01p.me.ui.HomeContract.Listener
import com.nowiwr01p.me.ui.HomeContract.State
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HeaderWithPhotoAndContacts(
    state: State,
    listener: Listener?
) {
    Contacts(
        state = state,
        listener = listener
    )
    NamePosition()
    LocationTimezone()
}

/**
 * CONTACTS ROW
 */
@Composable
private fun Contacts(
    state: State,
    listener: Listener?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 14.dp)
            .fillMaxWidth()
    ) {
        state.contacts.forEach { contact ->
            ContactItem(
                contact = contact,
                onContactClick = { listener?.onContactClick(contact) }
            )
        }
    }
}

@Composable
private fun ContactItem(
    contact: ContactData,
    onContactClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onContactClick() }
    ) {
        Text(
            text = stringResource(contact.name).uppercase(),
            color = colorText,
            style = AppTypography.h4,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 18.dp)
        )
    }
}

/**
 * NAME AND POSITION
 */
 @Composable
private fun NamePosition() {
    Text(
        text = "Andrey Larionov | Android Developer",
        color = colorAccent,
        style = AppTypography.h1
    )
}

/**
 * LOCATION AND TIMEZONE INFO
 */
 @Composable
private fun LocationTimezone() {
    Text(
        text = "UTC+4 | Georgia, Tbilisi | GMT+4",
        color = colorText,
        style = AppTypography.h5,
        modifier = Modifier.padding(top = 24.dp)
    )
}