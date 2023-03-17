package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.DriveFolder
import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersUpdateRequest(
    val folderId: String,
    val name: String,
    val parentId: String? = null
)

typealias DriveFoldersUpdateResponse = DriveFolder
