package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Field
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountField(override val innerData: Field, override val hashCode: Int) :
    MultiAccountData<Field>, Field(innerData.name, innerData.value, innerData.isVerified)
