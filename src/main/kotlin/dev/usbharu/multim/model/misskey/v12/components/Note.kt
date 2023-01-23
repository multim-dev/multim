package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Note(
    val id: String,
    val text: String? = null,
    val cw: String? = null,
    val userId: String,
    val user: UserLite,
    val replyId: String? = null,
    val renoteId: String? = null,
    @Transient
    val reply: Note? = null,
    @Transient
    val renote: Note? = null,
    val isHidden: Boolean? = null,
    val visibility: String = "public",
    val mentions: List<String>? = listOf(),
    val visibleUserIds: List<String>? = listOf(),
    val fileIds: List<String>? = listOf(),
    val files: List<File>? = listOf(),
    val tags: List<String>? = listOf(),
    val poll: Poll? = null,
    val channelId: String? = null,
    val channel: Channel? = null,
    val localOnly: Boolean? = null,
    val emojis: List<EmojiLite> = emptyList(),
    val reactions: Reactions = mapOf(),
    val renoteCount: Int = 0,
    val repliesCount: Int = 0,
    val uri: String? = null,
    val url: String? = null,
    val myReaction: MyReaction? = null
) {
    @Serializable
    data class Channel(val id: String, val name: String?)

    @Serializable
    class MyReaction

    @Serializable
    class Poll
}

typealias Reactions = Map<String, Int>