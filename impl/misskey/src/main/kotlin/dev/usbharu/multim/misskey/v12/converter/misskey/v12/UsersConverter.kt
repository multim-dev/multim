package dev.usbharu.multim.misskey.v12.converter.misskey.v12

import dev.usbharu.multim.misskey.v12.common.*
import dev.usbharu.multim.misskey.v12.model.UsersRelationResponse
import dev.usbharu.multim.misskey.v12.model.components.*

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
