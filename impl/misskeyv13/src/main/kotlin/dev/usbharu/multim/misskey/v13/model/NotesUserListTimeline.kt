package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v13.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class NotesUserListTimelineRequest(
    val listId: String,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null,
    val sinceDate: Long? = null,
    val untilDate: Long? = null,
    val includeMyRenotes: Boolean = true,
    val includeRenotedMyNotes: Boolean = true,
    val includeLocalRenoted: Boolean = true,
    val withFiles: Boolean = false
) : MisskeyNeedAuth()

typealias NotesUserListTimelineResponse = List<Note>
