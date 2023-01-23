package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Muting(
    val id: String,
    val createdAt: Instant,
    val expiresAt: Instant?,
    val muteeId: String,
    val mutee: UserDetailed
)
