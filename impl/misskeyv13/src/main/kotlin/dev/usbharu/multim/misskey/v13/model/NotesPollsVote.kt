package dev.usbharu.multim.misskey.v13.model

import kotlinx.serialization.Serializable

@Serializable
data class NotesPollsVoteRequest(
    val noteId: String,
    val choice: Int
)
