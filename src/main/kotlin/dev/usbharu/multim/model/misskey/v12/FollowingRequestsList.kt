package dev.usbharu.multim.model.misskey.v12

import dev.usbharu.multim.model.misskey.v12.components.UserLite

typealias FollowingRequestsListResponse = List<FollowingRequestsList>

@kotlinx.serialization.Serializable
data class FollowingRequestsList(
    val id: String,
    val follower: UserLite,
    val followee: UserLite
)