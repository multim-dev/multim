package dev.usbharu.multim.misskey.v13.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class UserList(
    val id: String,
    val createdAt: Instant,
    val name: String,
    val userIds: List<String>?
)
