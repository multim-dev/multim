package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesLocalTimelineRequest(
    val withFiles: Boolean = false,
    val fileType: List<String> = emptyList(),
    val excludeNsfw: Boolean? = false,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null,
    val sinceDate: Long? = null,
    val untilDate: Long? = null
)

typealias NotesLocalTimelineResponse = List<Note>
