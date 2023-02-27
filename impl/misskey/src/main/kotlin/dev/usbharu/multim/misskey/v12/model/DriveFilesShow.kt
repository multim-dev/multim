package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFile
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesShowRequestByFileId(
    val fileId: String
)

@Serializable
data class DriveFilesShowRequestByUrl(
    val url: String
)

typealias DriveFilesShowResponse = DriveFile
