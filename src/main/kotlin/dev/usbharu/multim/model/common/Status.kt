package dev.usbharu.multim.model.common

abstract class Status(
    open val id: StatusId,
    open val account: Account,
    val content: StatusContent,
    val reactions: Map<Reaction, Int> = emptyMap(),
    val myReactions: List<Reaction> = emptyList(),
    val repostCount: Int = 0,
    val repliesCount: Int = 0,
    val reposted: Boolean = false,
    val emojis: List<Emoji> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val language: String? = null
)

abstract class StatusForPost(
    open val account: Account,
    open val content: StatusContent
)
