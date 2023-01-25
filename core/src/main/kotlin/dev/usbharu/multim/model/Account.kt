package dev.usbharu.multim.model

abstract class Account(
    val screenName: String,
    val accountName: String,
    val isBot: Boolean? = false,
    val avatar: Avatar
)
