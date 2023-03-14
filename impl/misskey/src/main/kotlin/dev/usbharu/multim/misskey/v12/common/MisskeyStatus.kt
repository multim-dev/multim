package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

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
    createdAt:Instant = Clock.System.now()
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
    files,
    createdAt
)
