package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingDeleteRequest(
    val userId: String
)

typealias FollowingDeleteResponse = UserLite
