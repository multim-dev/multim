package dev.usbharu.multim.misskey.v13.model

import dev.usbharu.multim.misskey.v13.model.components.DriveFile
import kotlinx.serialization.Serializable

@Serializable
data class DriveStreamRequest(
    val limit: Int = 10,
    val sinceId: String,
    val untilId: String,
    val type: String
)

typealias DriveStreamResponse = List<DriveFile>
