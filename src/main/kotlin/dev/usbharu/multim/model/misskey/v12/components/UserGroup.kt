package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserGroup(
    val id: String,
    val createdAt: Instant,
    val name: String,
    val ownerId: String,
    val userIds: List<String>? = null
)
