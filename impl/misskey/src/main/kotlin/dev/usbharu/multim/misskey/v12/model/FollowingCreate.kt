package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v12.model.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingCreateRequest(
    val userId: String
) : MisskeyNeedAuth()

typealias FollowingCreateResponse = UserLite
