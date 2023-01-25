package dev.usbharu.multim.misskey.v12.converter.misskey.v12

import dev.usbharu.multim.misskey.v12.common.*
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.UserLite
import dev.usbharu.multim.model.Status

object NoteConverter {
    fun Note.toStatus(): MisskeyStatus {
        return MisskeyStatus(
            id = MisskeyStatusId(id, url ?: ""),
            account = MisskeyAccount(
                id = user.id,
                screenName = user.name ?: user.username,
                accountName = user.username,
                isBot = user.isBot,
                avatar = MisskeyAvatar(avatarUrl = user.avatarUrl ?: "")
            ),
            content = MisskeyContent(text ?: "")
        )
    }

    fun Note.fromStatus(status: Status): Note {
        if (status is MisskeyStatus) {
            return Note(
                id = status.id.id,
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
