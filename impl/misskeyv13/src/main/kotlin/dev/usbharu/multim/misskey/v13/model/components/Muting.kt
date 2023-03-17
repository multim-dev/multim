package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Muting(
    val id: String,
    val createdAt: Instant,
    val expiresAt: Instant?,
    val muteeId: String,
    val mutee: UserDetailed
)
