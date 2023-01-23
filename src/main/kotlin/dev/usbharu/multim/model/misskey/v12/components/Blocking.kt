package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Blocking(
    val id: String,
    val createdAt: Instant,
    val blockeeId: String,
    val blockee: UserDetailed
)
