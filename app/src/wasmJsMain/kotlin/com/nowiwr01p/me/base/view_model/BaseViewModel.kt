package com.nowiwr01p.me.base.view_model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.nowiwr01p.me.base.state.BaseEffect
import com.nowiwr01p.me.base.state.BaseEvent
import com.nowiwr01p.me.base.state.BaseState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    private val viewState = MutableStateFlow(this.setInitialState())

    abstract fun setInitialState(): State

    @Composable
    fun withState() = viewState.collectAsState().value

    suspend fun getState() = mutex.withLock { viewState }

    protected suspend fun setState(reducer: State.() -> State) = mutex.withLock {
        val newState = viewState.value.reducer()
        viewState.value = newState
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
    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    protected fun setEffect(builder: () -> Effect) = coroutineScope.launch(dispatcher) {
        _effect.emit(builder())
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