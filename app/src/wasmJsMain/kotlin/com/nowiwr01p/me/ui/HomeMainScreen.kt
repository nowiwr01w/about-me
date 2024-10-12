package com.nowiwr01p.me.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nowiwr01p.me.base.view_model.rememberViewModel
import com.nowiwr01p.me.core_ui.extensions.appendLink
import com.nowiwr01p.me.core_ui.extensions.onTextClick
import com.nowiwr01p.me.core_ui.theme.params.colorAccent
import com.nowiwr01p.me.core_ui.theme.params.colorBackground
import com.nowiwr01p.me.core_ui.theme.params.colorBackgroundLight
import com.nowiwr01p.me.core_ui.theme.params.colorLink
import com.nowiwr01p.me.core_ui.theme.params.colorText
import com.nowiwr01p.me.resources.Res
import com.nowiwr01p.me.resources.avatar
import com.nowiwr01p.me.resources.ic_drop_down_arrow
import com.nowiwr01p.me.shared.calendar.CalendarDay
import com.nowiwr01p.me.shared.contact.ContactData
import com.nowiwr01p.me.ui.HomeContract.Event
import com.nowiwr01p.me.ui.HomeContract.Listener
import com.nowiwr01p.me.ui.HomeContract.State
import com.nowiwr01p.me.ui.data.Details
import com.nowiwr01p.me.ui.data.WorkExperience
import com.nowiwr01p.me.ui.data.calendar.Calendar
import com.nowiwr01p.me.ui.data.pet_project.PetProjectData
import com.nowiwr01p.me.ui.data.tech_stack.TechStackData
import com.nowiwr01p.me.ui.data.tech_stack.techProficiences
import com.nowiwr01p.me.ui.data.workExperienceItems
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val MAX_CONTENT_WIDTH = 900.dp

@Composable
internal fun HomeMainScreen(
    viewModel: HomeViewModel = rememberViewModel()
) {
    val listener = object : Listener {
        override fun onContactClick(contact: ContactData) {

        }
        override fun onDateClick(date: CalendarDay.Available) {
            viewModel.setEvent(Event.OnCalendarDateClicked(date))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    val state = viewModel.withState()
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground)
            .verticalScroll(scrollState)
    ) {
        Content(
            state = state,
            listener = listener,
            scrollState = scrollState
        )
        ContactsFooter(
            state = state,
            listener = listener
        )
    }
}

@Composable
private fun Content(
    state: State,
    listener: Listener?,
    scrollState: ScrollState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .animateContentSize(animationSpec = tween(durationMillis = 500))
    ) {
        HeaderWithPhotoAndContacts(
            state = state,
            listener = listener,
            scrollState = scrollState
        )
        homeItem {
            Divider(topPadding = 16.dp)
            Description()
            Divider()
            Education()
            Divider()
            TechStack()
            Divider()
        }
        WorkExperience()
        homeItem {
            Divider()
            PetProjectInfo()
            Divider()
            FriendshipTitle()
            FriendshipDescription()
            Calendar(
                state = state,
                listener = listener
            )
        }
    }
}

@Composable
private fun HeaderWithPhotoAndContacts(
    state: State,
    listener: Listener?,
    scrollState: ScrollState
) {
    Avatar()
    NamePosition()
    LocationTimezone(scrollState)
}

/**
 * AVATAR
 */
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
private fun LocationTimezone(scrollState: ScrollState) {
    val scope = rememberCoroutineScope()
    val contactMeClickableText = "Contact me"
    val annotatedText = buildAnnotatedString {
        append("UTC+4 | ")
        append("Georgia, Tbilisi") // TODO: Translate with resources
        append(" | ")
        appendLink(contactMeClickableText)
    }
    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.h5.copy(color = colorText),
        modifier = Modifier.padding(top = 16.dp),
        onClick = { offset ->
            annotatedText.onTextClick(
                text = contactMeClickableText,
                offset = offset,
                onClick = {
                    scope.launch {
                        scrollState.animateScrollTo(
                            value = scrollState.maxValue,
                            animationSpec = tween(durationMillis = 750)
                        )
                    }
                }
            )
        }
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
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        homeItem(isContentCentered = false) {
            SectionTitle("Work experience")
        }
        workExperienceItems.forEachIndexed { index, data ->
            WorkItem(data)
            if (index != workExperienceItems.lastIndex) {
                homeItem {
                    WorkItemDivider()
                }
            }
        }
    }
}

@Composable
private fun WorkItem(workExperience: WorkExperience) {
    val showWorkExperienceDetails = remember {
        val isPetProject = workExperience is PetProjectData
        val stillWorkingThere = with(workExperience.projectInfo) {
            details is Details.Dates && details.endDate == "Present" // TODO: Replace with resource®
        }
        mutableStateOf(stillWorkingThere || isPetProject)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 500))
    ) {
        CompanyPositionEnrollDates(
            showWorkExperienceDetails = showWorkExperienceDetails,
            companyName = workExperience.companyInfo.name,
            position = workExperience.projectInfo.position,
            details = workExperience.projectInfo.details
        )
        AnimatedVisibility(
            visible = showWorkExperienceDetails.value,
            enter = fadeIn(animationSpec = tween()),
            exit = fadeOut(animationSpec = tween())
        ) {
            homeItem(isContentCentered = false) {
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
 * LET'S BE FRIENDS TITLE AND DESCRIPTION
 */
@Composable
private fun FriendshipTitle() {
    Text(
        text = "Let\'s be friends?",
        color = colorAccent,
        style = MaterialTheme.typography.h1,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun FriendshipDescription() {
    Text(
        text = "Here you can schedule a call with me\n Just select a date and relax, I will be notified instantly",
        color = colorText,
        style = MaterialTheme.typography.body1.copy(lineHeight = 28.sp),
        maxLines = 2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 16.dp)
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
    showWorkExperienceDetails: MutableState<Boolean>,
    companyName: String,
    position: String,
    details: Details
) {
    val showDropDownArrow = details is Details.Dates
    val startPaddingFromArrow = if (showDropDownArrow) 16.dp else 0.dp
    val dropDownArrowContainerWidth = if (showDropDownArrow) 40.dp else 0.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = dropDownArrowContainerWidth + startPaddingFromArrow)
            .width(MAX_CONTENT_WIDTH + dropDownArrowContainerWidth + startPaddingFromArrow),
    ) {
        if (showDropDownArrow) {
            val dropDownArrowRotationDegrees by animateFloatAsState(
                targetValue = if (showWorkExperienceDetails.value) 180f else 0f
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(dropDownArrowContainerWidth)
                    .clip(CircleShape)
                    .clickable {
                        showWorkExperienceDetails.value = !showWorkExperienceDetails.value
                    }
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_drop_down_arrow),
                    contentDescription = "Show or hide work experience details",
                    colorFilter = ColorFilter.tint(colorBackgroundLight),
                    modifier = Modifier
                        .size(dropDownArrowContainerWidth / 2)
                        .rotate(dropDownArrowRotationDegrees)
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(start = startPaddingFromArrow)
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
 * COLUMN WRAP TO MANAGE MAX ITEM WIDTH (except WorkExperience)
 */
@Composable
private fun homeItem(
    isContentCentered: Boolean = true,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.width(MAX_CONTENT_WIDTH),
        horizontalAlignment = when {
            isContentCentered -> Alignment.CenterHorizontally
            else -> Alignment.Start
        }
    ) {
        content()
    }
}

/**
 * PREVIEW
 */
@Preview
@Composable
private fun Preview() = Content(
    state = State(),
    listener = null,
    scrollState = rememberScrollState()
)