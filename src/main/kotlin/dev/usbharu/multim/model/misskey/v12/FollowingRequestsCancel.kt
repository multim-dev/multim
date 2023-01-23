package dev.usbharu.multim.model.misskey.v12

@kotlinx.serialization.Serializable
data class FollowingRequestsCancelRequest(
    val userId: String
)