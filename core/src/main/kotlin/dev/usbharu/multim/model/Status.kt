package dev.usbharu.multim.model

abstract class Status(
    open val id: StatusId,
    open val account: Account,
    val content: Content,
    val reactions: Map<Reaction, Int> = emptyMap(),
    val myReactions: List<Reaction> = emptyList(),
    val repostCount: Int = 0,
    val repliesCount: Int = 0,
    val reposted: Boolean = false,
    val emojis: List<Emoji> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val language: String? = null,
    val poll: Poll? = null,
    val files: Files? = null
)

abstract class StatusForPost(
    open val account: Account,
    open val content: Content,
    open val poll: PollForPost? = null,
    open val files: FilesForPost? = null
)
