package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Reaction
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountReaction(override val innerData: Reaction, override val hashCode: Int) :
    MultiAccountData<Reaction>,
    Reaction(innerData.name, innerData.url)
