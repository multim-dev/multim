package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesMentionsRequest(
    val following: Boolean = false,
    val limit: Int = 10,
    val sinceId: String,
    val untilId: String,
    val visibility: String
)

typealias NotesMentionsResponse = List<Note>
