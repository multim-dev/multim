package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesFeaturedRequest(
    val limit: Int = 10,
    val offset: Int = 0
)

typealias NotesFeaturedResponse = List<Note>
