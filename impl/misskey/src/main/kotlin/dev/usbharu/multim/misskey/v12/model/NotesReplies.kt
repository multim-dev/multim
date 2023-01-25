package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesRepliesRequest(
    val noteId: String,
    val sinceId: String? = null,
    val untilId: String? = null,
    val limit: Int = 10
)

typealias NotesRepliesResponse = List<Note>
