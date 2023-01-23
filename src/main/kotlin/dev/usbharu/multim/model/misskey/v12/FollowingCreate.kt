package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingCreateRequest(
    val userId: String
)

typealias FollowingCreateResponse = UserLite
