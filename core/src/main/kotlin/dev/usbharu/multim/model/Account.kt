package dev.usbharu.multim.model

import dev.usbharu.multim.cache.Cacheable

abstract class Account(
    val screenName: String,
    val accountName: String,
    val isBot: Boolean? = false,
    val avatar: Avatar
) : Cacheable
