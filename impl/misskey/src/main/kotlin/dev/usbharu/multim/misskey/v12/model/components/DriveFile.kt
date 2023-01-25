package dev.usbharu.multim.misskey.v12.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class DriveFile(
    val id: String,
    val createdAt: Instant,
    val name: String,
    val type: String,
    val md5: String,
    val size: Int,
    val isSensitive: Boolean,
    val blurhash: String?,
    val properties: Properties,
    val url: String?,
    val thumbnailUrl: String?,
    val comment: String?,
    val folderId: String?,
    val folder: DriveFolder? = null,
    val userId: String?,
    val user: UserLite?
)

@Serializable
data class Properties(
    val width: Int? = null,
    val height: Int? = null,
    val orientation: Int? = null,
    val avgColor: String? = null
)
