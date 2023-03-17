package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.DriveFolder
import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersCreateRequest(
    val name: String = "Untitled",
    val parentId: String? = null
)

typealias DriveFoldersCreateResponse = DriveFolder
