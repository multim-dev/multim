package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.User

@kotlinx.serialization.Serializable
data class UsersShowRequest(
    val userId: String
)
typealias UsersShowResponse = User
