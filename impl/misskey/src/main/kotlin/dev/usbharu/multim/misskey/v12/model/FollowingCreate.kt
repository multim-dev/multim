package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingCreateRequest(
    val userId: String
)

typealias FollowingCreateResponse = UserLite
