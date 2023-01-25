package dev.usbharu.multim.misskey.v12.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GalleryPost(
    val id: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val title: String,
    val description: String?,
    val userId: Boolean,
    val user: UserLite,
    val fileIds: List<String>? = listOf(),
    val files: List<DriveFile>? = listOf(),
    val tags: List<String>? = listOf(),
    val isSensitive: Boolean
)
