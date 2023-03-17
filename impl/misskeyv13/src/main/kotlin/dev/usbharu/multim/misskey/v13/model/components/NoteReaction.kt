package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class NoteReaction(
    val id: String,
    val createdAt: Instant,
    val user: UserLite,
    val type: String
)
