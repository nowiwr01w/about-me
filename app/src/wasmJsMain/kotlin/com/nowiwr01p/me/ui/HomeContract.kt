package com.nowiwr01p.me.ui

import com.nowiwr01p.me.base.state.BaseEffect
import com.nowiwr01p.me.base.state.BaseEvent
import com.nowiwr01p.me.base.state.BaseState
import com.nowiwr01p.me.shared.ContactData

interface HomeContract {
    /**
     * EVENTS
     */
    sealed interface Event : BaseEvent {
        data object Init: Event
    }

    /**
     * STATE
     */
    data class State(
        val contacts: List<ContactData> = listOf()
    ) : BaseState

    /**
     * EFFECTS
     */
    sealed interface Effect : BaseEffect {

    }

    /**
     * LISTENERS
     */
    interface Listener : ContactsListener

    interface ContactsListener {
        fun onContactClick(contact: ContactData)
    }
}