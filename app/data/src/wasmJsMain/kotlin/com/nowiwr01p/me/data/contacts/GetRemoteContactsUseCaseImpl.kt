package com.nowiwr01p.me.data.contacts

import com.nowiwr01p.me.domain.contacts.GetContactsUseCase
import com.nowiwr01p.me.shared.contact.ContactData

class GetRemoteContactsUseCaseImpl : GetContactsUseCase {

    override suspend fun execute(input: Unit): List<ContactData> {
        TODO()
    }
}