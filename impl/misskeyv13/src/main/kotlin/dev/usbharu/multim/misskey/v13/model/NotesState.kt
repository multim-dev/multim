package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class NotesStateRequest(
    val noteId: String,
) : MisskeyNeedAuth()

@Serializable
data class NotesStateResponse(
    val isFavorited: Boolean,
    val isMutedThread: Boolean
)
