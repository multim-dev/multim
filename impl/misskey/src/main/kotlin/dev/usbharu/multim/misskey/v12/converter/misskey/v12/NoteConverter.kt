package dev.usbharu.multim.misskey.v12.converter.misskey.v12

import dev.usbharu.multim.misskey.v12.common.*
import dev.usbharu.multim.misskey.v12.converter.misskey.v12.ReactionConverter.toReactions
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.UserLite
import dev.usbharu.multim.model.Status

object NoteConverter {
    fun Note.toStatus(): MisskeyStatus {
        return MisskeyStatus(
            id = MisskeyStatusId(id, url.orEmpty()),
            account = MisskeyAccount(
                id = user.id,
                screenName = user.name ?: user.username,
                accountName = user.username,
                isBot = user.isBot,
                avatar = MisskeyAvatar(avatarUrl = user.avatarUrl.orEmpty())
            ),
            content = MisskeyContent(text.orEmpty()),
            files = files?.map { MisskeyFile(byteArrayOf(), it.type, it.url.orEmpty()) }
                ?.let { MisskeyFiles(it) },
            reactions = reactions.toReactions(this),
            repostCount = renoteCount,
            repliesCount = repliesCount,
            reposted = false,
            createdAt = createdAt
        )
    }

    fun Note.Companion.fromStatus(status: Status): Note {
        if (status is MisskeyStatus) {
            return Note(
                id = status.id.id,
                text = status.content.text,
                createdAt = status.createdAt,
                userId = status.account.id,
                user = UserLite(
                    id = status.account.id,
                    name = status.account.screenName,
                    username = status.account.accountName
                )
            )
        } else {
            TODO()
        }
    }
}
