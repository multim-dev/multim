package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class DriveFolder(
    val id: String,
    val createdAt: LocalDateTime,
    val name: String,
    val foldersCount: Int? = null,
    val filesCount: Int? = null,
    val parentId: String?,
    val parent: DriveFolder? = null
)
