package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.DriveFolder
import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersFindRequest(
    val name: String,
    val parentId: String? = null
)

typealias DriveFoldersFindResponse = List<DriveFolder>
