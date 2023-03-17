package dev.usbharu.multim.misskey.v13.common

import dev.usbharu.multim.model.Account
import dev.usbharu.multim.model.Content
import dev.usbharu.multim.model.Field
import dev.usbharu.multim.model.Profile

class MisskeyProfile(
    account: Account,
    isBot: Boolean = false,
    profileContent: Content,
    followingCount: Int = 0,
    followersCount: Int = 0,
    fields: List<Field> = emptyList()
) : Profile(account, isBot, profileContent, followingCount, followersCount, fields)
