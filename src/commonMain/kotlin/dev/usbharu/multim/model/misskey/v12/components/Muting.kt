package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Muting(
    val id: String,
    val createdAt: LocalDateTime,
    val expiresAt: LocalDateTime?,
    val muteeId: String,
    val mutee: UserDetailed
)
