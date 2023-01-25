package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFile
import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesRequest(
    val limit: Int = 10,
    val sinceId: String,
    val untilId: String,
    val folderId: String? = null,
    val type: String? = null
) : MisskeyNeedAuth()

typealias DriveFilesResponse = List<DriveFile>
