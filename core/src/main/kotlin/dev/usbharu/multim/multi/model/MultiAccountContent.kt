package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Content
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountContent(override val innerData: Content, override val hashCode: Int) :
    MultiAccountData<Content>, Content(innerData.text)
