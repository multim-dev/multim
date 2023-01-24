package dev.usbharu.multim.misskey.v12.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Clip(
    val id: String,
    val createdAt: Instant,
    val userId: String,
    val user: UserLite,
    val name: String,
    val description: String?,
    val isPublic: Boolean
)
