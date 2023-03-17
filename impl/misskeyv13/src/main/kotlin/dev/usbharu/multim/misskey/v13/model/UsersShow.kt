package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.User

@kotlinx.serialization.Serializable
data class UsersShowRequest(
    val userId: String
)
typealias UsersShowResponseV13 = User
