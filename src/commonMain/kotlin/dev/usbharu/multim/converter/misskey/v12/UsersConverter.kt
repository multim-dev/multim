package dev.usbharu.multim.converter.misskey.v12

import dev.usbharu.multim.model.common.impl.misskey.MisskeyAccount
import dev.usbharu.multim.model.common.impl.misskey.MisskeyRelation
import dev.usbharu.multim.model.misskey.v12.UsersRelationResponse

object UsersConverter {
    fun UsersRelationResponse.toRelation(
        myself: MisskeyAccount,
        other: MisskeyAccount
    ): MisskeyRelation {

        return MisskeyRelation(myself, other, isFollowing, isFollowed, isMuted, isBlocked)
    }
}