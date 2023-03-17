package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.DriveFolder
import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersRequest(
    val limit: Int,
    val sinceId: String,
    val untilId: String,
    val folderId: String? = null
)

typealias DriveFoldersResponse = DriveFolder
