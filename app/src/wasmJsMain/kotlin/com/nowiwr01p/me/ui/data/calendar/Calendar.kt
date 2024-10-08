package com.nowiwr01p.me.ui.data.calendar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.VerticalPager
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.params.colorAccent
import com.nowiwr01p.me.core_ui.theme.params.colorBackgroundLight
import com.nowiwr01p.me.core_ui.theme.params.colorText
import com.nowiwr01p.me.shared.calendar.CalendarDay
import com.nowiwr01p.me.shared.calendar.CalendarDay.Available
import com.nowiwr01p.me.shared.calendar.CalendarDay.Hidden
import com.nowiwr01p.me.shared.calendar.CalendarDay.ShortDayName
import com.nowiwr01p.me.shared.calendar.CalendarDay.Unavailable
import com.nowiwr01p.me.shared.calendar.CalendarMonth
import com.nowiwr01p.me.ui.Divider
import com.nowiwr01p.me.ui.HomeContract.Listener
import com.nowiwr01p.me.ui.HomeContract.State

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Calendar(
    state: State,
    listener: Listener?
) {
    if (state.calendarState.calendar.isEmpty()) {
        return
    }
    val density = LocalDensity.current
    var page by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState { state.calendarState.calendar.size }
    var displayedMonth by remember { mutableStateOf(state.calendarState.calendar.first()) }
    val calendarGridWidth = remember { mutableStateOf(0.dp) }

    LaunchedEffect(page) {
        displayedMonth = state.calendarState.calendar[page]
        pagerState.animateScrollToPage(
            page = page,
            animationSpec = tween(500)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        CalendarHeader(
            month = displayedMonth,
            onPreviousArrowClick = { page -= 1 },
            onNextArrowClick = { page += 1 },
            isFirstMonth = page == 0,
            isLastMonth = page == state.calendarState.calendar.lastIndex,
            maxWidth = calendarGridWidth
        )
        VerticalPager(
            state = pagerState,
            beyondBoundsPageCount = 1,
            userScrollEnabled = false,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 64.dp)
                .heightIn(max = 290.dp)
        ) {
            CalendarDates(
                month = displayedMonth,
                selectedDay = state.calendarState.selectedDate,
                onDateClick = { listener?.onDateClick(it) },
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
    onPreviousArrowClick: () -> Unit,
    onNextArrowClick: () -> Unit,
    isFirstMonth: Boolean,
    isLastMonth: Boolean,
    maxWidth: MutableState<Dp>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 64.dp)
            .width(maxWidth.value)
    ) {
        MonthNameWithArrows(
            month = month,
            onPreviousArrowClick = onPreviousArrowClick,
            onNextArrowClick = onNextArrowClick,
            isFirstMonth = isFirstMonth,
            isLastMonth = isLastMonth,
        )
        Divider(topPadding = 16.dp)
        DaysNameRow()
    }
}

@Composable
private fun MonthNameWithArrows(
    month: CalendarMonth,
    onPreviousArrowClick: () -> Unit,
    onNextArrowClick: () -> Unit,
    isFirstMonth: Boolean,
    isLastMonth: Boolean
) {
    val previousMonthArrowColor by animateColorAsState(
        targetValue = if (isFirstMonth) colorBackgroundLight else colorText
    )
    val nextMonthArrowColor by animateColorAsState(
        targetValue = if (isLastMonth) colorBackgroundLight else colorText
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack),
            contentDescription = "Previous month arrow",
            colorFilter = ColorFilter.tint(previousMonthArrowColor),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .clickable(enabled = !isFirstMonth) { onPreviousArrowClick() }
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
            colorFilter = ColorFilter.tint(nextMonthArrowColor),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .clickable(enabled = !isLastMonth) { onNextArrowClick() }
        )
    }
}

@Composable
private fun DaysNameRow() = Row(
    modifier = Modifier.padding(top = 16.dp)
) {
    listOf("M", "T", "W", "T", "F", "S", "S").map { ShortDayName(it) }.forEach { // TODO: Use resources
        CalendarDay(it, selectedDay = null)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

/**
 * GRID CALENDAR DATES
 */
@Composable
private fun CalendarDates(
    month: CalendarMonth,
    selectedDay: CalendarDay?,
    onDateClick: (date: Available) -> Unit,
    gridWidthModifier: Modifier
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .widthIn(max = 390.dp)
                .then(gridWidthModifier)
        ) {
            items(month.days) { date ->
                CalendarDay(
                    calendarDay = date,
                    selectedDay = selectedDay,
                    onDateClick = if (date !is Available) null else {{
                        onDateClick(date)
                    }}
                )
            }
        }
    }
}

@Composable
private fun CalendarDay(
    calendarDay: CalendarDay,
    selectedDay: CalendarDay?,
    onDateClick: (() -> Unit)? = null
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (calendarDay == selectedDay) colorAccent else Color.Transparent,
        animationSpec = tween(durationMillis = 500)
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(enabled = calendarDay is Available) {
                onDateClick?.invoke()
            }
    ) {
        val text = when (calendarDay) {
            is Hidden -> "  "
            is Available -> calendarDay.day
            is Unavailable -> calendarDay.day
            is ShortDayName -> calendarDay.name
        }
        val textColor = when (calendarDay) {
            is Hidden, is Unavailable -> colorBackgroundLight
            is ShortDayName, is Available -> colorText
        }
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}