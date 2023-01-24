package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class NotesWatchingDeleteRequest(
    val noteId: String
) : MisskeyNeedAuth()
