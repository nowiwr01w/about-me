package com.nowiwr01p.me.ui.data.calendar

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