package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class NotesUnrenoteRequest(
    val noteId: String
) : MisskeyNeedAuth()
