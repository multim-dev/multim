package dev.usbharu.multim.misskey.v12.model.components

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class DriveFolder(
    val id: String,
    val createdAt: Instant,
    val name: String,
    val foldersCount: Int? = null,
    val filesCount: Int? = null,
    val parentId: String?,
    val parent: DriveFolder? = null
)
