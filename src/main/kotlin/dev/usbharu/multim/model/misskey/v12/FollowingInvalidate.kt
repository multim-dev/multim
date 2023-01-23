package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingInvalidateRequest(
    val userId: String
)

typealias FollowingInvalidateResponse = UserLite