package com.nowiwr01p.me.ui.data.calendar

import kotlinx.datetime.*

fun getDatesForCurrentAndNextMonth(): List<CalendarMonth> {
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    val currentMonth = LocalDate(
        year = today.year,
        monthNumber = today.monthNumber,
        dayOfMonth = 1
    )
    val nextMonth = currentMonth.plus(DatePeriod(months = 1))

    return listOf(
        getCalendarGridForMonth(todayDay = today.dayOfMonth, month = currentMonth, isCurrent = true),
        getCalendarGridForMonth(todayDay = today.dayOfMonth, month = nextMonth, isCurrent = false)
    )
}

private fun getCalendarGridForMonth(
    todayDay: Int,
    month: LocalDate,
    isCurrent: Boolean
): CalendarMonth {
    val firstDayOfMonth = LocalDate(
        year = month.year,
        monthNumber = month.monthNumber,
        dayOfMonth = 1
    )
    val lastDayOfMonth = firstDayOfMonth.plus(DatePeriod(months = 1, days = -1))

    val daysBeforeMonthStart = List(firstDayOfMonth.dayOfWeek.ordinal) { CalendarDay.Hidden }
    val daysAfterMonthEnd = List(6 - lastDayOfMonth.dayOfWeek.ordinal) { CalendarDay.Hidden }

    val daysInMonth = (1..lastDayOfMonth.dayOfMonth).map { day ->
        val dayString = day.toString().padStart(length = 2, padChar = '0')
        if (isCurrent && day < todayDay) {
            CalendarDay.Unavailable(dayString)
        } else {
            CalendarDay.Available(dayString)
        }
    }
    return CalendarMonth(
        name = month.month.name,
        days = daysBeforeMonthStart + daysInMonth + daysAfterMonthEnd
    )
}

data class MeetingTiming(
    val id: Int,
    val time: Long
)

private fun getAvailableMeetingsTimings() = listOf(
    MeetingTiming(id = 1, time = 1),
    MeetingTiming(id = 2, time = 1),
    MeetingTiming(id = 3, time = 1),
    MeetingTiming(id = 4, time = 1)
)

data class CalendarMonth(
    val name: String,
    val days: List<CalendarDay>
)

sealed interface CalendarDay {
    data object Hidden : CalendarDay
    data class ShortDayName(val name: String) : CalendarDay
    data class Unavailable(val day: String) : CalendarDay
    data class Available(val day: String) : CalendarDay // TODO: Add MeetingTiming later
}