package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.DriveFile
import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesFindRequest(
    val name: String,
    val folderId: String? = null
) : MisskeyNeedAuth()

typealias DriveFilesFindResponse = List<DriveFile>
