package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Avatar
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountAvatar(override val innerData: Avatar, override val hashCode: Int) :
    MultiAccountData<Avatar>, Avatar(innerData.avatarUrl)
