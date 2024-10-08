package com.nowiwr01p.me.ui

import com.nowiwr01p.me.base.state.BaseEffect
import com.nowiwr01p.me.base.state.BaseEvent
import com.nowiwr01p.me.base.state.BaseState
import com.nowiwr01p.me.shared.calendar.CalendarDay
import com.nowiwr01p.me.shared.calendar.CalendarDay.*
import com.nowiwr01p.me.shared.calendar.CalendarMonth
import com.nowiwr01p.me.shared.contact.ContactData

interface HomeContract {
    /**
     * EVENTS
     */
    sealed interface Event : BaseEvent {
        data object Init : Event
        data class OnCalendarDateClicked(val date: Available) : Event
    }

    /**
     * STATE
     */
    data class State(
        val contacts: List<ContactData> = listOf(),
        val calendarState: CalendarState = CalendarState()
    ) : BaseState

    data class CalendarState(
        val calendar: List<CalendarMonth> = listOf(),
        val selectedDate: Available? = null
    )

    /**
     * EFFECTS
     */
    sealed interface Effect : BaseEffect {

    }

    /**
     * LISTENERS
     */
    interface Listener : ContactsListener, CalendarListener

    interface ContactsListener {
        fun onContactClick(contact: ContactData)
    }

    interface CalendarListener {
        fun onDateClick(date: Available)
    }
}