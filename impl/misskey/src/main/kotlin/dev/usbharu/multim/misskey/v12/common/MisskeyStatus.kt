package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.*

@Suppress("LongParameterList")
class MisskeyStatus(
    override val id: MisskeyStatusId,
    override val account: MisskeyAccount,
    content: Content,
    reactions: Map<Reaction, Int> = emptyMap(),
    myReactions: List<Reaction> = emptyList(),
    repostCount: Int = 0,
    repliesCount: Int = 0,
    reposted: Boolean = false,
    emojis: List<Emoji> = emptyList(),
    tags: List<Tag> = emptyList(),
    language: String? = null,
    poll: Poll? = null,
    files: Files? = null,
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
    language,
    poll,
    files
)
