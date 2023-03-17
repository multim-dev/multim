package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.Serializable


@Serializable
data class NotesNotesRequest(
    val local: Boolean = false,
    val reply: Boolean? = null,
    val renote: Boolean? = null,
    val withFiles: Boolean? = null,
    val poll: Boolean? = null,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null
)

typealias NotesNotesResponse = List<Note>
