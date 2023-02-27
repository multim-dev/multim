package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Option
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountOption(override val innerData: Option, override val hashCode: Int) :
    MultiAccountData<Option>,
    Option(innerData.title, innerData.votes, innerData.voted)
