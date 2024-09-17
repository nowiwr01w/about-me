package com.nowiwr01p.me.shared

import com.nowiwr01p.me.resources.*
import org.jetbrains.compose.resources.StringResource

interface ContactDomain {
    val name: StringResource
    val redirectUrl: String
}

sealed class ContactDomainLocal(
    override val name: StringResource,
    override val redirectUrl: String
): ContactDomain {

    data object Email : ContactDomainLocal(
        name = Res.string.contact_email,
        redirectUrl = "https://google.com"
    )
    data object LinkedIn : ContactDomainLocal(
        name = Res.string.contact_linkedin,
        redirectUrl = "https://google.com"
    )
    data object Github : ContactDomainLocal(
        name = Res.string.contact_github,
        redirectUrl = "https://google.com"
    )
    data object Medium : ContactDomainLocal(
        name = Res.string.contact_medium,
        redirectUrl = "https://google.com"
    )
    data object LeetCode : ContactDomainLocal(
        name = Res.string.contact_leetcode,
        redirectUrl = "https://google.com"
    )
    data object Telegram : ContactDomainLocal(
        name = Res.string.contact_telegram,
        redirectUrl = "https://google.com"
    )
}

class ContactDomainRemote(
    override val name: StringResource,
    override val redirectUrl: String
): ContactDomain