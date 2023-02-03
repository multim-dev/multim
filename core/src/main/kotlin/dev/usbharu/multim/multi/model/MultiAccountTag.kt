package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Tag
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountTag(override val innerData: Tag, override val hashCode: Int) : MultiAccountData<Tag>,
    Tag(innerData.text) {
}
