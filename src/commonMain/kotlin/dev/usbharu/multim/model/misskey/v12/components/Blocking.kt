package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Blocking(
    val id: String,
    val createdAt: LocalDateTime,
    val blockeeId: String,
    val blockee: UserDetailed
)
