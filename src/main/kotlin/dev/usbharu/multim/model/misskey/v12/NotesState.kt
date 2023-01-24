package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class NotesStateRequest(
    val noteId: String,
) : MisskeyNeedAuth()

@Serializable
data class NotesStateResponse(
    val isFavorited: Boolean,
    val isWatching: Boolean,
    val isMutedThread: Boolean
)
