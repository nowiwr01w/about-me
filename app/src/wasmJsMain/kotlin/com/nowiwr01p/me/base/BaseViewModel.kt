package com.nowiwr01p.me.base

import androidx.compose.runtime.State as ComposeState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseViewModel<Event: BaseEvent, State: BaseState, Effect: BaseEffect>(
    private val coroutineScope: CoroutineScope // each screen scope
) {
    private val mutex = Mutex()
    private val dispatcher = Dispatchers.Default

    /**
     * STATE
     */
    private val _viewState by lazy { mutableStateOf(initialState) }
    val viewState by lazy<ComposeState<State>> { _viewState }

    private val initialState by lazy { setInitialState() }
    abstract fun setInitialState(): State

    protected suspend fun setState(reducer: State.() -> State) = mutex.withLock {
        val newState = _viewState.value.reducer()
        _viewState.value = newState
    }

    /**
     * EVENT
     */
    private val _event = MutableSharedFlow<Event>()

    private fun subscribeToEvents() = coroutineScope.launch(dispatcher) {
        _event.collect { event -> handleEvents(event) }
    }
    abstract fun handleEvents(event: Event)

    fun setEvent(event: Event) = coroutineScope.launch(dispatcher) {
        _event.emit(event)
    }
    init {
        subscribeToEvents()
    }

    /**
     * EFFECT
     */
    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    protected fun setEffect(builder: () -> Effect) = coroutineScope.launch(dispatcher) {
        _effect.send(builder())
    }

    /**
     * EXECUTE CODE IN ANOTHER THREAD
     */
    fun BaseViewModel<*, *, *>.io(
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return coroutineScope.launch(dispatcher) { block() }
    }
    
    fun BaseViewModel<*, *, *>.io(
        handler: CoroutineExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return coroutineScope.launch(handler + dispatcher) { block() }
    }
}