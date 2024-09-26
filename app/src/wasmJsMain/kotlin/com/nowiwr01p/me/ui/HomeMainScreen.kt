package com.nowiwr01p.me.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nowiwr01p.me.core_ui.theme.params.*
import com.nowiwr01p.me.resources.Res
import com.nowiwr01p.me.resources.avatar
import com.nowiwr01p.me.shared.ContactData
import com.nowiwr01p.me.ui.HomeContract.*
import com.nowiwr01p.me.ui.data.Details
import com.nowiwr01p.me.ui.data.WorkExperience
import com.nowiwr01p.me.ui.data.pet_project.PetProjectData
import com.nowiwr01p.me.ui.data.tech_stack.TechStackData
import com.nowiwr01p.me.ui.data.tech_stack.techProficiences
import com.nowiwr01p.me.ui.data.workExperienceItems
import org.jetbrains.compose.resources.painterResource
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

    val state = viewModel.withState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .verticalScroll(rememberScrollState())
    ) {
        Content(
            state = state,
            listener = listener
        )
        ContactsFooter(state, listener)
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
        Divider(topPadding = 16.dp)
        Description()
        Divider()
        Education()
        Divider()
        TechStack()
        Divider()
        WorkExperience()
        Divider()
        PetProjectInfo()
        FriendshipTitle()
    }
}


@Composable
private fun Avatar() {
    Image(
        painter = painterResource(Res.drawable.avatar),
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 32.dp)
            .size(164.dp)
            .clip(CircleShape)
    )
}
@Composable
private fun HeaderWithPhotoAndContacts(
    state: State,
    listener: Listener?
) {
    Avatar()
    NamePosition()
    LocationTimezone()
}

/**
 * NAME AND POSITION
 */
 @Composable
private fun NamePosition() {
    Text(
        text = "Andrey Larionov",
        color = colorAccent,
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(top = 16.dp)
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
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(top = 16.dp)
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
        style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ITMO University, Russia",
                color = colorText,
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium)
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "2018 - 2022",
                color = colorText,
                style = MaterialTheme.typography.h4
            )
        }
        Text(
            text = "Applied Math and Computer Science",
            color = colorText.copy(alpha = 0.5f),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

/**
 * TECH STACK
 */
@Composable
private fun TechStack() {
    Column {
        SectionTitle("Technical proficienses")
        techProficiences.forEachIndexed { index, data ->
            TechStackItem(data)
            if (index != techProficiences.lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun TechStackItem(data: TechStackData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = data.category,
            color = colorText,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = data.skills,
            color = colorText,
            style = MaterialTheme.typography.body1.copy(
                lineHeight = 32.sp,
                lineHeightStyle = null,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.weight(5f)
        )
    }
}

/**
 * WORK EXPERIENCE
 */
@Composable
private fun WorkExperience() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionTitle("Work experience")
        workExperienceItems.forEachIndexed { index, data ->
            WorkItem(data)
            if (index != workExperienceItems.lastIndex) {
                WorkItemDivider()
            }
        }
    }
}

@Composable
private fun WorkItem(workExperience: WorkExperience) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CompanyPositionEnrollDates(
            companyName = workExperience.companyInfo.name,
            position = workExperience.projectInfo.position,
            details = workExperience.projectInfo.details
        )
        Text(
            text = workExperience.companyInfo.description,
            color = colorText,
            style = MaterialTheme.typography.body1.copy(lineHeight = 24.sp),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        workExperience.projectInfo.tasks.forEachIndexed { index, task ->
            Text(
                text = "- $task",
                color = colorText,
                style = MaterialTheme.typography.body1.copy(lineHeight = 24.sp)
            )
            if (index != workExperience.projectInfo.tasks.lastIndex) {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun WorkItemDivider() = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxWidth()
        .height(49.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.1f)
            .height(1.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorText.copy(alpha = 0.2f))
    )
}

/**
 * PET PROJECT
 */
@Composable
private fun PetProjectInfo() {
    Column {
        SectionTitle("Pet project")
        WorkItem(PetProjectData)
    }
}

/**
 * LET'S BE FRIENDS TITLE
 */
@Composable
private fun FriendshipTitle() {
    Text(
        text = "Let\'s be friends?",
        color = colorAccent,
        style = MaterialTheme.typography.h1,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(vertical = 64.dp)
            .fillMaxWidth()
    )
}

/**
 * CONTACTS FOOTER
 */
@Composable
private fun ContactsFooter(
    state: State,
    listener: Listener?
) {
    val shape = remember { RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(164.dp)
            .clip(shape)
            .background(
                color = colorBackgroundLight,
                shape = shape
            )
    ) {
        Contacts(
            state = state,
            listener = listener
        )
    }
}

@Composable
private fun Contacts(
    state: State,
    listener: Listener?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 24.dp)
            .width(900.dp)
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
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

/**
 * SECTION TITLE
 */
@Composable
private fun SectionTitle(text: String) = Text(
    text = text,
    color = colorAccent,
    style = MaterialTheme.typography.h2,
    textAlign = TextAlign.Start,
    modifier = Modifier
        .padding(top = 32.dp, bottom = 24.dp)
        .fillMaxWidth()
)

/**
 * COMPANY NAME AND ENROLLED DATES
 */
@Composable
private fun CompanyPositionEnrollDates(
    companyName: String,
    position: String,
    details: Details
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(
                    color = Color(0xFF5E2B9D),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Text(
                text = companyName,
                color = colorText,
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
            )
        }
        Text(
            text = position,
            color = colorText.copy(alpha = 0.5f),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = when (details) {
                is Details.Dates -> "${details.startDate} - ${details.endDate}"
                is Details.ProjectLink -> "Github"
            },
            color = when (details) {
                is Details.Dates -> colorText
                is Details.ProjectLink -> colorLink
            },
            style = MaterialTheme.typography.h4,
            modifier = Modifier.clickable(enabled = details is Details.ProjectLink) {
                // TODO: Add click logic
            }
        )
    }
}

/**
 * HORIZONTAL DIVIDER
 */
@Composable
internal fun Divider(topPadding: Dp = 32.dp) = Box(
    modifier = Modifier
        .padding(top = topPadding)
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