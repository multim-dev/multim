package dev.usbharu.multim.model.misskey.v12

import kotlinx.serialization.Serializable

@Serializable
data class NotesStateRequest(
    val noteId: String,
)

@Serializable
data class NotesStateResponse(
    val isFavorited: Boolean,
    val isWatching: Boolean,
    val isMutedThread: Boolean
)
