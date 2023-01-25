package dev.usbharu.multim.misskey.v12.model

@kotlinx.serialization.Serializable
data class FollowingRequestsCancelRequest(
    val userId: String
)
