package com.nowiwr01p.me.data.contacts

import com.nowiwr01p.me.domain.contacts.GetContactsUseCase
import com.nowiwr01p.me.shared.contact.ContactData.*

class GetLocalContactsUseCaseImpl : GetContactsUseCase {

    override suspend fun execute(input: Unit) = listOf(
        Email,
        LinkedIn,
        Github,
        Medium,
        LeetCode,
        Telegram
    )
}