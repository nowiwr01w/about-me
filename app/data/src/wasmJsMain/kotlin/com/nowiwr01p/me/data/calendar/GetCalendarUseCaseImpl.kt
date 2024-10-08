package com.nowiwr01p.me.data.calendar

import com.nowiwr01p.me.domain.calendar.GetCalendarUseCase
import com.nowiwr01p.me.shared.calendar.CalendarDay.Available
import com.nowiwr01p.me.shared.calendar.CalendarDay.Hidden
import com.nowiwr01p.me.shared.calendar.CalendarDay.Unavailable
import com.nowiwr01p.me.shared.calendar.CalendarMonth
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class GetCalendarUseCaseImpl : GetCalendarUseCase {

    override suspend fun execute(input: Unit): List<CalendarMonth> {
        val today = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date

        val currentMonth = LocalDate(
            year = today.year,
            monthNumber = today.monthNumber,
            dayOfMonth = 1
        )
        val nextMonth = currentMonth.plus(DatePeriod(months = 1))

        return mapOf(currentMonth to true, nextMonth to false).map { (month, isCurrent) ->
            getCalendarGridForMonth(
                month = month,
                isCurrent = isCurrent,
                todayDay = today.dayOfMonth
            )
        }
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

        val daysBeforeMonthStart = List(firstDayOfMonth.dayOfWeek.ordinal) { Hidden }
        val daysAfterMonthEnd = List(6 - lastDayOfMonth.dayOfWeek.ordinal) { Hidden }

        val daysInMonth = (1..lastDayOfMonth.dayOfMonth).map { day ->
            val dayString = day.toString().padStart(length = 2, padChar = '0')
            if (isCurrent && day < todayDay) {
                Unavailable(dayString)
            } else {
                Available(
                    day = dayString,
                    month = month.month.name,
                    isCurrentDay = day == todayDay
                )
            }
        }
        return CalendarMonth(
            name = month.month.name,
            days = daysBeforeMonthStart + daysInMonth + daysAfterMonthEnd
        )
    }
}