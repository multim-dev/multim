package dev.usbharu.multim.converter.misskey.v12

import dev.usbharu.multim.model.common.Status
import dev.usbharu.multim.model.common.impl.misskey.*
import dev.usbharu.multim.model.misskey.v12.components.Note
import dev.usbharu.multim.model.misskey.v12.components.UserLite

object NoteConverter {
    fun Note.toStatus(): MisskeyStatus {
        return MisskeyStatus(
            id = MisskeyStatusId(id, url ?: ""),
            account = MisskeyAccount(
                id = user.id,
                screenName = user.name,
                accountName = user.username,
                isBot = user.isBot,
                avatar = MisskeyAvatar(avatarUrl = user.avatarUrl ?: "")
            ),
            content = MisskeyContent(text ?: "")
        )
    }

    fun Note.Companion.fromStatus(status: Status): Note {
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
