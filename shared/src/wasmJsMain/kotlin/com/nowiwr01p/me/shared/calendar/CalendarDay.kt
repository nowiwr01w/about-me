package com.nowiwr01p.me.shared.calendar

sealed interface CalendarDay {
    data object Hidden : CalendarDay
    data class ShortDayName(val name: String) : CalendarDay
    data class Unavailable(val day: String) : CalendarDay
    data class Available(
        val day: String,
        val month: String,
        val isCurrentDay: Boolean
    ) : CalendarDay // TODO: Add MeetingTiming later
}