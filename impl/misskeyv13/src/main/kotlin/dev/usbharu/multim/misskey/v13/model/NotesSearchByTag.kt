package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.Serializable

/**
 * Notes search by tag request
 *
 * @property tag No Empty
 * @constructor Create empty Notes search by tag request
 */
@Serializable
data class NotesSearchByTagRequest(
    val tag: String,
    val reply: Boolean? = null,
    val renote: Boolean? = null,
    val withFiles: Boolean = false,
    val poll: Boolean? = null,
    val sinceId: String? = null,
    val untilId: String? = null,
    val limit: Int = 10
)

typealias NotesSearchByTagResponse = List<Note>
