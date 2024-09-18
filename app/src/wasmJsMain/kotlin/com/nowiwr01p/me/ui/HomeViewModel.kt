package com.nowiwr01p.me.ui

import com.nowiwr01p.me.base.view_model.BaseViewModel
import com.nowiwr01p.me.ui.HomeContract.*
import kotlinx.coroutines.CoroutineScope

class HomeViewModel(scope: CoroutineScope) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {

    }
}