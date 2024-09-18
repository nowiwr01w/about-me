package com.nowiwr01p.me.shared

import com.nowiwr01p.me.resources.*
import org.jetbrains.compose.resources.StringResource

sealed class ContactData(
    val name: StringResource,
    val redirectUrl: String
) {
    data object Email : ContactData(
        name = Res.string.contact_email,
        redirectUrl = "https://google.com"
    )
    data object LinkedIn : ContactData(
        name = Res.string.contact_linkedin,
        redirectUrl = "https://google.com"
    )
    data object Github : ContactData(
        name = Res.string.contact_github,
        redirectUrl = "https://google.com"
    )
    data object Medium : ContactData(
        name = Res.string.contact_medium,
        redirectUrl = "https://google.com"
    )
    data object LeetCode : ContactData(
        name = Res.string.contact_leetcode,
        redirectUrl = "https://google.com"
    )
    data object Telegram : ContactData(
        name = Res.string.contact_telegram,
        redirectUrl = "https://google.com"
    )
}