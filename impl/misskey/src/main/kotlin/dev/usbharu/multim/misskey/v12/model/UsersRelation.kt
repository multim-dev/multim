package dev.usbharu.multim.misskey.v12.model

@kotlinx.serialization.Serializable
data class UsersRelationRequest(val userId: String)

@kotlinx.serialization.Serializable
data class UsersRelationResponse(
    val id: String,
    val isFollowing: Boolean,
    val isFollowed: Boolean,
    val hasPendingFollowRequestFromYou: Boolean,
    val hasPendingFollowRequestToYou: Boolean,
    val isBlocking: Boolean,
    val isBlocked: Boolean,
    val isMuted: Boolean
)
