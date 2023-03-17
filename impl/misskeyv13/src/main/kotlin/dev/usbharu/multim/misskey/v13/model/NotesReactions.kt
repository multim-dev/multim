package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.NoteReaction
import kotlinx.serialization.Serializable

@Serializable
data class NotesReactionsRequest(
    val noteId: String,
    val type: String? = null,
    val limit: Int = 10,
    val offset: Int = 0,
    val sinceId: String? = null,
    val untilId: String? = null
)

typealias NotesReactionsResponse = List<NoteReaction>
