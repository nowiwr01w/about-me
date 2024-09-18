package com.nowiwr01p.me.ui

import com.nowiwr01p.me.base.view_model.BaseViewModel
import com.nowiwr01p.me.domain.contacts.GetContactsUseCase
import com.nowiwr01p.me.domain.execute
import com.nowiwr01p.me.ui.HomeContract.*
import kotlinx.coroutines.CoroutineScope

class HomeViewModel(
    scope: CoroutineScope,
    private val getContactsUseCase: GetContactsUseCase
) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
        }
    }

    private fun init() {
        getContacts()
    }

    private fun getContacts() = io {
        runCatching {
            getContactsUseCase.execute()
        }.onSuccess { contacts ->
            setState { copy(contacts = contacts) }
        }
    }
}