package dev.usbharu.multim.model

abstract class Profile(
    val account: Account,
    val isBot: Boolean = false,
    val profileContent: Content,
    val followingCount: Int = 0,
    val followersCount: Int = 0,
    val fields: List<Field> = emptyList()
)
