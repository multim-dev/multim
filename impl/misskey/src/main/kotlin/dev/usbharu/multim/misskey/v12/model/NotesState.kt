package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
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
