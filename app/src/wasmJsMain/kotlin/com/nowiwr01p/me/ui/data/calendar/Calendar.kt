package com.nowiwr01p.me.ui.data.calendar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.params.colorBackgroundLight
import com.nowiwr01p.me.core_ui.theme.params.colorText
import com.nowiwr01p.me.ui.Divider

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Calendar(
    calendar: List<CalendarMonth> = getDatesForCurrentAndNextMonth()
) {
    val density = LocalDensity.current
    val pagerState = rememberPagerState { calendar.size }
    var displayedMonth by remember { mutableStateOf(calendar.first()) }
    val calendarGridWidth = remember { mutableStateOf(0.dp) }

    LaunchedEffect(pagerState.currentPage) {
        displayedMonth = calendar[pagerState.currentPage]
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        CalendarHeader(
            month = displayedMonth,
            maxWidth = calendarGridWidth
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 64.dp)
                .fillMaxWidth()
        ) {
            CalendarDates(
                month = displayedMonth,
                gridWidthModifier = Modifier.onSizeChanged { size ->
                    calendarGridWidth.value = size.width.dp / density.density
                }
            )
        }
    }
}

/**
 * HEADER WITH MONTH NAME AND PREV/NEXT ARROWS
 */
@Composable
private fun CalendarHeader(
    month: CalendarMonth,
    maxWidth: MutableState<Dp>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 64.dp)
            .width(maxWidth.value)
    ) {
        MonthNameWithArrows(month)
        Divider(topPadding = 16.dp)
        DaysNameRow()
    }
}

@Composable
private fun MonthNameWithArrows(month: CalendarMonth) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack),
            contentDescription = "Previous month arrow",
            colorFilter = ColorFilter.tint(colorBackgroundLight),
            modifier = Modifier.size(24.dp)
        )
        Spacer(
            modifier = Modifier.weight(0.5f)
        )
        Crossfade(targetState = month) { month ->
            Text(
                text = month.name,
                color = colorText,
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Medium),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }
        Spacer(
            modifier = Modifier.weight(0.5f)
        )
        Image(
            painter = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowForward),
            contentDescription = "Next month arrow",
            colorFilter = ColorFilter.tint(colorBackgroundLight),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun DaysNameRow() = Row(
    modifier = Modifier.padding(top = 16.dp)
) {
    listOf("M", "T", "W", "T", "F", "S", "S").forEach { // TODO: Use resources
        CalendarIem(it)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

/**
 * GRID CALENDAR DATES
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CalendarDates(
    month: CalendarMonth,
    gridWidthModifier: Modifier
) {
    FlowRow(
        maxItemsInEachRow = 7,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .then(gridWidthModifier)
    ) {
        month.days.forEach { date ->
            CalendarIem(
                text = date?.dayOfMonth?.toString()?.padStart(2, '0') ?: "  ",
                onDateClick = {  }
            )
        }
    }
}

@Composable
private fun CalendarIem(
    text: String,
    onDateClick: (() -> Unit)? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .clickable(enabled = text.trim().isNotEmpty() && onDateClick != null) {
                onDateClick?.invoke()
            }
    ) {
        Text(
            text = text,
            color = colorText,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}