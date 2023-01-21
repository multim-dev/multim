package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingDeleteRequest(
    val userId: String
)

typealias FollowingDeleteResponse = UserLite
