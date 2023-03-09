package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v12.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class UsersNotesRequest(
    val userId: String,
    val includeReplies: Boolean = true,
    val limit: Int = 10,
    val sinceId: String? = null,
    val untilId: String? = null,
    val sinceDate: Long? = null,
    val untilDate: Long? = null,
    val includeMyRenotes: Boolean? = null,
    val withFile: Boolean? = false,
    val fileType: List<String> = emptyList(),
    val excludeNsfw: Boolean = false
) : MisskeyNeedAuth()


typealias UsersNotesResponse = List<Note>
