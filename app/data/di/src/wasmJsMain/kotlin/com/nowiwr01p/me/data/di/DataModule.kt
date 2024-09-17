package com.nowiwr01p.me.data.di

import GetRemoteContactsUseCaseImpl
import com.nowiwr01p.me.data.contacts.GetLocalContactsUseCaseImpl
import com.nowiwr01p.me.domain.contacts.GetContactsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleData = module {
    /**
     * CONTACTS
     */
    factory<GetContactsUseCase>(named("Local")) {
        GetLocalContactsUseCaseImpl()
    }
    factory<GetContactsUseCase>(named("Remote")) {
        GetRemoteContactsUseCaseImpl()
    }
}