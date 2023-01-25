package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFile
import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesCreateRequest(
    val folderId:String? = null,
    val name:String? = null,
    val comment:String? = null,
    val isSensitive:Boolean = false,
    val force:Boolean = false,
    val file:ByteArray
) : MisskeyNeedAuth()

typealias DriveFilesCreateResponse = DriveFile
