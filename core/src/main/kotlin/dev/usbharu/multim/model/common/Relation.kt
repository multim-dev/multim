package dev.usbharu.multim.model.common

abstract class Relation(
    val myself: Account,
    val other: Account,
    val following: Boolean,
    val follower: Boolean,
    val mute: Boolean,
    val block: Boolean
)
