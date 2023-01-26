package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.*

class MisskeyProfile(
    account: Account,
    isBot: Boolean = false,
    profileContent: Content,
    followingCount: Int = 0,
    followersCount: Int = 0,
    fields: List<Field> = emptyList()
) : Profile(account, isBot, profileContent, followingCount, followersCount, fields)
