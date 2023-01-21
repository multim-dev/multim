package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    val id: String,
    val createdAt: LocalDateTime,
    val lastNotedAt: LocalDateTime,
    val name: String,
    val description: String?,
    val bannerUrl: String,
    val notesCount: Int,
    val usersCount: Int,
    val isFollowing: Boolean,
    val userId: String?
)
