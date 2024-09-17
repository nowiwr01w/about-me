package com.nowiwr01p.me.domain.contacts

import com.nowiwr01p.me.domain.UseCase
import com.nowiwr01p.me.shared.ContactDomain

interface GetContactsUseCase : UseCase<Unit, List<ContactDomain>>