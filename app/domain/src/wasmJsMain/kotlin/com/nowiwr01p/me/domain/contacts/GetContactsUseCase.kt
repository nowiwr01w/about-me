package com.nowiwr01p.me.domain.contacts

import com.nowiwr01p.me.domain.UseCase
import com.nowiwr01p.me.shared.contact.ContactData

interface GetContactsUseCase : UseCase<Unit, List<ContactData>>