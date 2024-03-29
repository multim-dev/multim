package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class UserGroup(
    val id: String,
    val createdAt: Instant,
    val name: String,
    val ownerId: String,
    val userIds: List<String>? = null
)
