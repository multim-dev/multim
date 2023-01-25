package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.Account
import dev.usbharu.multim.model.Field
import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.model.ProfileContent

class MisskeyProfile(
    account: Account,
    isBot: Boolean = false,
    profileContent: ProfileContent,
    followingCount: Int = 0,
    followersCount: Int = 0,
    fields: List<Field> = emptyList()
) : Profile(account, isBot, profileContent, followingCount, followersCount, fields)
