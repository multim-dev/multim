package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingInvalidateRequest(
    val userId: String
)

typealias FollowingInvalidateResponse = UserLite
