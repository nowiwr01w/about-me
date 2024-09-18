package com.nowiwr01p.me.ui

import com.nowiwr01p.me.base.state.BaseEffect
import com.nowiwr01p.me.base.state.BaseEvent
import com.nowiwr01p.me.base.state.BaseState
import com.nowiwr01p.me.ui.header.data.ContactUi

interface HomeContract {

    sealed interface Event : BaseEvent {
        data object Init: Event
    }

    data class State(
        val contacts: List<ContactUi> = listOf()
    ) : BaseState

    sealed interface Effect : BaseEffect {

    }

    interface Listener {

    }
}