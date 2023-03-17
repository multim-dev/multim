package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.MisskeyNeedAuth
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesCheckExistenceRequest(
    val md5: String
) : MisskeyNeedAuth()

typealias DriveFilesCheckExistenceResponse = Boolean
