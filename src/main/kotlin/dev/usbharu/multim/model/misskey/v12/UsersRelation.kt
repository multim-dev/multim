package dev.usbharu.multim.model.misskey.v12

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