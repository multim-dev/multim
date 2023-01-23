package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.serialization.Serializable

@Serializable
data class QueueCount(
    val waiting: Int,
    val active: Int,
    val completed: Int,
    val failed: Int,
    val delayed: Int
)
