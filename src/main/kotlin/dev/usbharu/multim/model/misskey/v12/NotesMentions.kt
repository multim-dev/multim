package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.MisskeyNeedAuth
import dev.usbharu.multim.model.misskey.v12.components.Note
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
