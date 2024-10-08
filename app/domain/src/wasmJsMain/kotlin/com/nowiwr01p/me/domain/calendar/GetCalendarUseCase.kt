package com.nowiwr01p.me.domain.calendar

import com.nowiwr01p.me.domain.UseCase
import com.nowiwr01p.me.shared.calendar.CalendarMonth

interface GetCalendarUseCase: UseCase<Unit, List<CalendarMonth>>