package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Antenna(
    val id: String,
    val createdAt: LocalDateTime,
    val name: String,
    val keywords: List<List<String>>,
    val excludeKeywords: List<List<String>>,
    val src: Src,
    val userListId: String?,
    val userGroupId: String?,
    val users: List<String>,
    val caseSensitive: Boolean = false,
    val notify: Boolean,
    val withReplies: Boolean,
    val withFile: Boolean,
    val hasUnreadNote: Boolean = false
) {
    enum class Src(val string: String) {
        HOME("home"),
        ALL("all"),
        USERS("users"),
        LIST("list"),
        GROUP("group")
    }
}
