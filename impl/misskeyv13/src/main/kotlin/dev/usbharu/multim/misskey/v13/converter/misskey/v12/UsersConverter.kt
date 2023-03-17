package dev.usbharu.multim.misskey.v13.converter.misskey.v12

import dev.usbharu.multim.misskey.v13.model.UsersRelationResponse
import dev.usbharu.multim.misskey.v13.common.MisskeyAccount
import dev.usbharu.multim.misskey.v13.common.MisskeyAvatar
import dev.usbharu.multim.misskey.v13.common.MisskeyContent
import dev.usbharu.multim.misskey.v13.common.MisskeyField
import dev.usbharu.multim.misskey.v13.common.MisskeyProfile
import dev.usbharu.multim.misskey.v13.common.MisskeyRelation
import dev.usbharu.multim.misskey.v13.model.components.Field
import dev.usbharu.multim.misskey.v13.model.components.MeDetailed
import dev.usbharu.multim.misskey.v13.model.components.User
import dev.usbharu.multim.misskey.v13.model.components.UserDetailedNotMe
import dev.usbharu.multim.misskey.v13.model.components.UserLite

object UsersConverter {
    fun UsersRelationResponse.toRelation(
        myself: MisskeyAccount,
        other: MisskeyAccount
    ): MisskeyRelation {

        return MisskeyRelation(myself, other, isFollowing, isFollowed, isMuted, isBlocked)
    }

    fun User.toProfile(): MisskeyProfile {
        return when (this) {
            is MeDetailed -> {
                MisskeyProfile(
                    MisskeyAccount(
                        this.id,
                        this.name ?: "null",
                        this.username,
                        this.isBot,
                        MisskeyAvatar(this.avatarUrl ?: "null")
                    ),
                    this.isBot,
                    MisskeyContent(this.description ?: "null"),
                    this.followingCount,
                    this.followersCount,
                    this.fields.map { it.toField() }
                )
            }

            is UserDetailedNotMe -> {
                MisskeyProfile(
                    MisskeyAccount(
                        this.id,
                        this.name ?: "null",
                        this.username,
                        this.isBot,
                        MisskeyAvatar(this.avatarUrl ?: "null")
                    ),
                    this.isBot,
                    MisskeyContent(this.description ?: "null"),
                    this.followingCount,
                    this.followersCount,
                    this.fields.map { it.toField() }
                )
            }

            is UserLite -> {
                MisskeyProfile(
                    MisskeyAccount(
                        this.id,
                        this.name ?: "null",
                        this.username,
                        this.isBot,
                        MisskeyAvatar(this.avatarUrl ?: "null")
                    ), this.isBot ?: false, MisskeyContent("ERROR NO INFO")
                )
            }
        }
    }

    fun Field.toField(): MisskeyField {
        return MisskeyField(this.name, this.value)
    }
}
