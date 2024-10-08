package com.nowiwr01p.me.ui

import com.nowiwr01p.me.base.view_model.BaseViewModel
import com.nowiwr01p.me.domain.calendar.GetCalendarUseCase
import com.nowiwr01p.me.domain.contacts.GetContactsUseCase
import com.nowiwr01p.me.domain.execute
import com.nowiwr01p.me.shared.calendar.CalendarDay.Available
import com.nowiwr01p.me.ui.HomeContract.Effect
import com.nowiwr01p.me.ui.HomeContract.Event
import com.nowiwr01p.me.ui.HomeContract.Event.Init
import com.nowiwr01p.me.ui.HomeContract.Event.OnCalendarDateClicked
import com.nowiwr01p.me.ui.HomeContract.State
import kotlinx.coroutines.CoroutineScope

class HomeViewModel(
    scope: CoroutineScope,
    private val getContactsUseCase: GetContactsUseCase,
    private val getCalendarUseCase: GetCalendarUseCase
) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is OnCalendarDateClicked -> selectCalendarDate(event.date)
        }
    }

    private fun init() {
        getContacts()
        getCalendar()
    }

    /**
     * CONTACTS
     */
    private fun getContacts() = io {
        runCatching {
            getContactsUseCase.execute()
        }.onSuccess { contacts ->
            setState { copy(contacts = contacts) }
        }
    }

    /**
     * CALENDAR
     */
    private fun getCalendar() = io {
        runCatching {
            getCalendarUseCase.execute()
        }.onSuccess { calendar ->
            val initialSelectedDate = calendar.flatMap { it.days }
                .filterIsInstance<Available>()
                .find { it.isCurrentDay }
            val updatedCalendarState = getState().calendarState.copy(
                calendar = calendar,
                selectedDate = initialSelectedDate
            )
            setState { copy(calendarState = updatedCalendarState) }
        }
    }

    private fun selectCalendarDate(date: Available) = io {
        val updatedCalendarState = getState().calendarState.copy(selectedDate = date)
        setState { copy(calendarState = updatedCalendarState) }
    }
}