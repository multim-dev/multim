package dev.usbharu.multim.model

import dev.usbharu.multim.cache.Cacheable

abstract class StatusId : Cacheable {
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
    abstract fun getUrl(): String
}
