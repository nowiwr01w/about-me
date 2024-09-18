package com.nowiwr01p.me.di

import com.nowiwr01p.me.ui.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val moduleApp = module {
    /**
     * HOME
     */
    single { (scope: CoroutineScope) ->
        HomeViewModel(
            scope = scope,
            getContactsUseCase = get(named("Local"))
        )
    }
}