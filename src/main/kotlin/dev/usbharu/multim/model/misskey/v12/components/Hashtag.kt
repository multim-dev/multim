package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.serialization.Serializable

@Serializable
data class Hashtag(
    val tag: String,
    val mentionedUsersCount: Int,
    val mentionedLocalUsersCount: Int,
    val mentionedRemoteUserCount: Int,
    val attachedUsersCount: Int,
    val attachedLocalUsersCount: Int,
    val attachedRemoteUsersCount: Int
)
