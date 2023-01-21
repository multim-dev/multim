package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserList(
    val id: String,
    val createdAt: LocalDateTime,
    val name: String,
    val userIds: List<String>?
)
