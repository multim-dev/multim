package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesRepliesRequest(
    val noteId: String,
    val sinceId: String? = null,
    val untilId: String? = null,
    val limit: Int = 10
)

typealias NotesRepliesResponse = List<Note>
