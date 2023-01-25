package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.UserLite

typealias FollowingRequestsListResponse = List<FollowingRequestsList>

@kotlinx.serialization.Serializable
data class FollowingRequestsList(
    val id: String,
    val follower: UserLite,
    val followee: UserLite
)
