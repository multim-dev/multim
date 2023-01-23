package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.User

@kotlinx.serialization.Serializable
data class UsersShowRequest(
    val userId: String
)
typealias UsersShowResponse = User