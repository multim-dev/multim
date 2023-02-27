package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesCheckExistenceRequest(
    val md5: String
) : MisskeyNeedAuth()

typealias DriveFilesCheckExistenceResponse = Boolean
