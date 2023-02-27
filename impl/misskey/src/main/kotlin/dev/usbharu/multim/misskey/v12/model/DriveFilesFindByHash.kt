package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFile
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesFindByHashRequest(
    val md5: String
)

typealias DriveFilesFindByHashResponse = List<DriveFile>
