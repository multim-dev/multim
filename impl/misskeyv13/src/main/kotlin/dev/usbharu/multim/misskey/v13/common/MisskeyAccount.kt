package dev.usbharu.multim.misskey.v13.common

import dev.usbharu.multim.model.Account
import dev.usbharu.multim.model.Avatar

class MisskeyAccount(
    val id: String,
    screenName: String,
    accountName: String,
    isBot: Boolean? = false,
    avatar: Avatar
) : Account(screenName, accountName, isBot, avatar) {
    override val cacheKey: String
        get() = accountName
}
