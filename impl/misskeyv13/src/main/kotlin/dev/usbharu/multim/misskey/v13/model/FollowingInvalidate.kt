package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingInvalidateRequest(
    val userId: String
)

typealias FollowingInvalidateResponse = UserLite
