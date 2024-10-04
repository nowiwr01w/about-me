package com.nowiwr01p.me.ui.data.calendar

import kotlinx.datetime.*

fun getDatesForCurrentAndNextMonth(): List<List<LocalDate?>> {
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    val currentMonth = LocalDate(
        year = today.year,
        monthNumber = today.monthNumber,
        dayOfMonth = 1
    )
    val nextMonth = currentMonth.plus(DatePeriod(months = 1))

    return getCalendarGridForMonth(currentMonth) + getCalendarGridForMonth(nextMonth)
}

private fun getCalendarGridForMonth(month: LocalDate): List<List<LocalDate?>> {
    val firstDayOfMonth = LocalDate(
        year = month.year,
        monthNumber = month.monthNumber,
        dayOfMonth = 1
    )
    val lastDayOfMonth = firstDayOfMonth.plus(DatePeriod(months = 1, days = -1))

    val daysBeforeMonthStart = List(firstDayOfMonth.dayOfWeek.ordinal) { null }
    val daysAfterMonthEnd = List(6 - lastDayOfMonth.dayOfWeek.ordinal) { null }

    val daysInMonth = (1..lastDayOfMonth.dayOfMonth).map { day ->
        firstDayOfMonth.plus(DatePeriod(days = day - 1))
    }
    return (daysBeforeMonthStart + daysInMonth + daysAfterMonthEnd).chunked(7)
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