package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Source
import dev.usbharu.multim.multi.MultiAccountData

// todo Sealed classを辞めずに書く方法を探す
class MultiAccountSource(override val innerData: Source, override val hashCode: Int) :
    MultiAccountData<Source>, Source()
