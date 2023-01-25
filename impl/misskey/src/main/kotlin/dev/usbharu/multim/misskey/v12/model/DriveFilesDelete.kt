package dev.usbharu.multim.misskey.v12.model

import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesDeleteRequest(
    val fileId:String
)
