package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.common.*

class MisskeyStatus(
    override val id: MisskeyStatusId,
    override val account: MisskeyAccount,
    content: StatusContent,
    reactions: Map<Reaction, Int> = emptyMap(),
    myReactions: List<Reaction> = emptyList(),
    repostCount: Int = 0,
    repliesCount: Int = 0,
    reposted: Boolean = false,
    emojis: List<Emoji> = emptyList(),
    tags: List<Tag> = emptyList(),
    language: String? = null
) : Status(
    id,
    account,
    content,
    reactions,
    myReactions,
    repostCount,
    repliesCount,
    reposted,
    emojis,
    tags,
    language
)
