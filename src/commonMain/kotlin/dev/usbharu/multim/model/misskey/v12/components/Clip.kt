package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Clip(
    val id: String,
    val createdAt: LocalDateTime,
    val userId: String,
    val user: UserLite,
    val name: String,
    val description: String?,
    val isPublic: Boolean
)
