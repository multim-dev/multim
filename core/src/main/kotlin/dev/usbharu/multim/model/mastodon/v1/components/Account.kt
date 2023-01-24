package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Account(
    val id: String,
    val username: String,
    val acct: String,
    val url: String,
    @SerialName("display_name")
    val displayName: String,
    val note: String,
    val avatar: String,
    @SerialName("avatar_static")
    val avatarStatic: String,
    val header: String,
    @SerialName("header_static")
    val headerStatic: String,
    val locked: Boolean,
    val fields: Boolean,
    val emojis: List<CustomEmoji>,
    val bot: Boolean,
    val group: Boolean,
    val discoverable: Boolean?,
    val noindex: Boolean? = null,
    @Transient
    val moved: Account? = null,
    val suspended: Boolean? = null,
    val limited: Boolean? = null,
    @SerialName("created_at")
    val createdAt: LocalDateTime,
    @SerialName("last_status_at")
    val lastStatusAt: LocalDateTime?,
    @SerialName("statuses_count")
    val statusesCount: Int,
    @SerialName("followers_count")
    val followersCount: Int,
    @SerialName("following_count")
    val followingCount: Int,
    val source: Source? = null,
    val role: Role? = null
) {
    @Serializable
    data class Source(
        val note: String,
        val fields: List<Field>,
        val privacy: Privacy,
        val sensitive: Boolean,
        val language: String,
        @SerialName("follow_request_count")
        val followRequestCount: Int
    ) {
        @Serializable
        data class Field(
            val name: String,
            val value: String,
            val verifiedAt: LocalDateTime? = null
        )

        enum class Privacy(val string: String) {
            PUBLIC("public"),
            UNLISTED("unlisted"),
            PRIVATE("private"),
            DIRECT("direct")
        }
    }
}
