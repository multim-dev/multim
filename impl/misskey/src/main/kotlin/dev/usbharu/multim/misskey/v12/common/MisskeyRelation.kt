package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.Relation

class MisskeyRelation(
    myself: MisskeyAccount,
    other: MisskeyAccount,
    following: Boolean,
    follower: Boolean,
    mute: Boolean,
    block: Boolean
) : Relation(myself, other, following, follower, mute, block)
