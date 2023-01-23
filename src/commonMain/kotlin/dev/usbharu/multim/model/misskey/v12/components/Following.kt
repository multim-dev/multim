package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Following(
    val id: String,
    val createdAt: Instant,
    val followeeId: String,
    val followee: UserDetailed? = null,
    val followerId: String,
    val follower: UserDetailed? = null
)
