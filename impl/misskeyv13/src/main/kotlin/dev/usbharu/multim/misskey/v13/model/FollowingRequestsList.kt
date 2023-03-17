package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.UserLite

typealias FollowingRequestsListResponse = List<FollowingRequestsList>


// todo なんか変なので治す
@kotlinx.serialization.Serializable
data class FollowingRequestsList(
    val id: String,
    val follower: UserLite,
    val followee: UserLite
)
