package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v13.model.components.UserLite

@kotlinx.serialization.Serializable
data class FollowingDeleteRequest(
    val userId: String
) : MisskeyNeedAuth()

typealias FollowingDeleteResponse = UserLite
