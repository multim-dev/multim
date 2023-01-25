package dev.usbharu.multim.misskey.v12.converter.misskey.v12

import dev.usbharu.multim.misskey.v12.common.MisskeyAccount
import dev.usbharu.multim.misskey.v12.common.MisskeyRelation
import dev.usbharu.multim.misskey.v12.model.UsersRelationResponse

object UsersConverter {
    fun UsersRelationResponse.toRelation(
        myself: MisskeyAccount,
        other: MisskeyAccount
    ): MisskeyRelation {

        return MisskeyRelation(myself, other, isFollowing, isFollowed, isMuted, isBlocked)
    }
}
