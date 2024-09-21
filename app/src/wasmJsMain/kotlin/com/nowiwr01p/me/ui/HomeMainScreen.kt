package com.nowiwr01p.me.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nowiwr01p.me.core_ui.theme.AppTypography
import com.nowiwr01p.me.core_ui.theme.colorAccent
import com.nowiwr01p.me.core_ui.theme.colorBackground
import com.nowiwr01p.me.core_ui.theme.colorText
import com.nowiwr01p.me.shared.ContactData
import com.nowiwr01p.me.ui.HomeContract.*
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rememberViewModel

@Composable
internal fun HomeMainScreen(
    viewModel: HomeViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onContactClick(contact: ContactData) {

        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
    ) {
        Content(
            state = viewModel.withState(),
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
        HeaderWithPhotoAndContacts(
            state = state,
            listener = listener
        )
        Divider()
        Description()
        Divider()
        Education()
        Divider()
    }
}

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

/**
 * DESCRIPTION
 */
@Composable
private fun Description() {
    Text(
        text = """
        Hi, I’m an Android Developer with 6 years of experience. I build modern, efficient apps using tools like Jetpack Compose, Coroutines, DataStore and WorkManager.
        I’m currently exploring KMM to create cross-platform apps for Android, iOS, Desktop and Web. I can also set up a Ktor server, integrate it with Postgres and deploy it to hosting.
        I’m passionate about the TON ecosystem and am interested in writing smart contracts as part of my exploration in blockchain development.
        """.trimIndent(),
        color = colorText,
        style = AppTypography.h3.copy(lineHeight = 32.sp),
        modifier = Modifier.padding(top = 32.dp)
    )
}

/**
 * EDUCATION
 */
@Composable
private fun Education() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionTitle(
            text = "Education"
        )
        CompanyNameWithStartEndDates(
            companyName = "ITMO University, Russia",
            startDate = "Sep 2018",
            endDate = "March 2022"
        )
        Text(
            text = "Applied Mathematics and Computer Science",
            color = colorText.copy(alpha = 0.5f),
            style = AppTypography.h5.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

/**
 * SECTION TITLE
 */
@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        color = colorAccent,
        style = AppTypography.h2,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .padding(top = 32.dp, bottom = 24.dp)
            .fillMaxWidth()
    )
}

/**
 * COMPANY NAME AND ENROLLED DATES
 */
@Composable
private fun CompanyNameWithStartEndDates(
    companyName: String,
    startDate: String,
    endDate: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = companyName,
            color = colorText,
            style = AppTypography.h3
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$startDate - $endDate",
            color = colorText,
            style = AppTypography.h3
        )
    }
}

/**
 * HORIZONTAL DIVIDER
 */
@Composable
internal fun Divider() = Box(
    modifier = Modifier
        .padding(top = 32.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .height(1.dp)
        .background(colorText.copy(alpha = 0.2f))
)

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = Content(
    state = State(),
    listener = null
)