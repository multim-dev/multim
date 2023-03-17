package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Blocking(
    val id: String,
    val createdAt: Instant,
    val blockeeId: String,
    val blockee: UserDetailed
)
