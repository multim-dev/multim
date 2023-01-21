package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesRenoteRequest(
    val noteId: String,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null
)

typealias NotesRenoteResponse = List<Note>
