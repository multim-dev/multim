package dev.usbharu.multim.mastodon.model.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Status(
    val id: String,
    val uri: String,
    val createdAt: LocalDateTime,
    val account: Account,
    val content: String,
    val visibility: Visibility,
    val sensitive: Boolean,
    val spoilerText: String,
    val mediaAttachments: List<MediaAttachment>,
    val application: Application? = null,
    val mentions: List<Mention>,
    val tags: List<Tag>,
    val emojis: List<CustomEmoji>,
    val reblogsCount: Int,
    val favoritesCount: Int,
    val repliesCount: Int,
    val url: String?,
    val inReplyToId: String?,
    val inReplyToAccountId: String?,
    @Transient
    val reblog: Status? = null,
    val poll: Poll?,
    val card: PreviewCard?,
    val language: String?,
    val text: String?,
    val editedAt: LocalDateTime?,
    val favourited: Boolean? = null,
    val reblogged: Boolean? = null,
    val muted: Boolean? = null,
    val bookmarked: Boolean? = null,
    val pinned: Boolean? = null,
    val filtered: List<FilterResult>? = null
) {
    enum class Visibility

    @Serializable
    data class Mention(
        val id: String,
        val username: String,
        val url: String,
        val acct: String
    )

    @Serializable
    data class Tag(
        val name: String,
        val url: String
    )

    @Serializable
    data class Application(
        val name: String,
        val website: String?
    )
}
