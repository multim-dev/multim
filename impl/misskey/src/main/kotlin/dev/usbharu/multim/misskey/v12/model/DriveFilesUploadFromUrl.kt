package dev.usbharu.multim.misskey.v12.model

import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesUploadFromUrlRequest(
    val url:String,
    val folderId:String? = null,
    val isSensitive:Boolean = false,
    val comment:String? = null,
    val marker:String? = null,
    val force:Boolean = false
)
