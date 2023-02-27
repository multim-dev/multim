package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Timeline
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountTimeline(override val innerData: Timeline, override val hashCode: Int) :
    MultiAccountData<Timeline>, Timeline(innerData.name)
