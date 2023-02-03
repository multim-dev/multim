package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Emoji
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountEmoji(override val innerData: Emoji, override val hashCode: Int) :
    MultiAccountData<Emoji>, Emoji(innerData.name, innerData.url)
