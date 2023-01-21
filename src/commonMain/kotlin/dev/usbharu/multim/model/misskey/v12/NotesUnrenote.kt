package dev.usbharu.multim.model.misskey.v12

import kotlinx.serialization.Serializable

@Serializable
data class NotesUnrenoteRequest(
    val noteId: String
)
