package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    val id: String,
    val createdAt: Instant,
    val lastNotedAt: Instant,
    val name: String,
    val description: String?,
    val bannerUrl: String,
    val notesCount: Int,
    val usersCount: Int,
    val isFollowing: Boolean,
    val userId: String?
)
