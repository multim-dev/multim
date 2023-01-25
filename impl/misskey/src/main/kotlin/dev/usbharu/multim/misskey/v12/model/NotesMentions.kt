package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v12.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesMentionsRequest(
    val following: Boolean = false,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null,
    val visibility: String? = null
) : MisskeyNeedAuth()

typealias NotesMentionsResponse = List<Note>
