package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFile
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesUpdateRequest(
    val fileId:String,
    val folderId:String? = null,
    val name:String,
    val isSensitive:Boolean,
    val comment:String? = null
)

typealias DriveFilesUpdateResponse = DriveFile
