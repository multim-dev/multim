package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NoteReaction(
    val id: String,
    val createdAt: Instant,
    val user: UserLite,
    val type: String
)
