package dev.usbharu.multim.misskey.v12.model


import dev.usbharu.multim.misskey.v12.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesSearchRequest(
    val query: String,
    val sinceId: String? = null,
    val untilId: String? = null,
    val limit: Int = 10,
    val offset: Int = 0,
    val host: String? = null,
    val userId: String? = null,
    val channelId: String? = null
)

typealias NotesSearchResponse = List<Note>
