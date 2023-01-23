package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.MisskeyNeedAuth
import dev.usbharu.multim.model.misskey.v12.components.Note
import kotlinx.serialization.Serializable

/**
 * Notes create request
 *
 * 組み合わせに対して、安全ではないです。
 *
 * @property visibility
 * @property visibleUserIds
 * @property text
 * @property cw
 * @property localOnly
 * @property noExtractMentions
 * @property noExtractHashTag
 * @property noExtractEmojis
 * @property fileIds
 * @property mediaIds
 * @property replyId
 * @property renoteId
 * @property channelId
 * @property poll
 * @constructor Create empty Notes create request
 */
@Serializable
data class NotesCreateRequest(
    val visibility: Visibility = Visibility.PUBLIC,
    val visibleUserIds: Set<String> = setOf(),
    val text: String? = null,
    val cw: String? = null,
    val localOnly: Boolean = false,
    val noExtractMentions: Boolean = false,
    val noExtractHashTag: Boolean = false,
    val noExtractEmojis: Boolean = false,
    val fileIds: Set<String>? = null,
    @Deprecated("Use fileIds instead. If both are specified, this property is discarded.")
    val mediaIds: Set<String>? = null,
    val replyId: String? = null,
    val renoteId: String? = null,
    val channelId: String? = null,
    val poll: Poll? = null,

    ) : MisskeyNeedAuth() {
    enum class Visibility(val string: String) {
        PUBLIC("public"),
        HOME("home"),
        FOLLOWERS("followers"),
        SPECIFIED("SPECIFIED")
    }

    @Serializable
    data class Poll(
        val choices: Set<String>,
        val multiple: Boolean = false,
        val expiresAt: Long? = null,
        val expiredAfter: Long? = null
    )
}

@Serializable
data class NotesCreateResponse(
    val createdNote: Note
)
