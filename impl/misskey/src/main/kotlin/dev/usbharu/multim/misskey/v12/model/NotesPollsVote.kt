package dev.usbharu.multim.misskey.v12.model

import kotlinx.serialization.Serializable

@Serializable
data class NotesPollsVoteRequest(
    val noteId: String,
    val choice: Int
)
