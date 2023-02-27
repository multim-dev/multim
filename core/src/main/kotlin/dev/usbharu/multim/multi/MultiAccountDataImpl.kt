package dev.usbharu.multim.multi

class MultiAccountDataImpl<T>(override val innerData: T, override val hashCode: Int) :
    MultiAccountData<T>
