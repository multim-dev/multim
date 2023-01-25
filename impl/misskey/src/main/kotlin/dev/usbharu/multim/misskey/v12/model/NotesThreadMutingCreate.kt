package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class NotesThreadMutingCreateRequest(
    val noteId: String
) : MisskeyNeedAuth()
