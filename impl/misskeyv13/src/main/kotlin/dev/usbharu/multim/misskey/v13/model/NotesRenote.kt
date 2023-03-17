package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesRenoteRequest(
    val noteId: String,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null
)

typealias NotesRenoteResponse = List<Note>
