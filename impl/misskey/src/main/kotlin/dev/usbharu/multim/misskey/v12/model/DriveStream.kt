package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFile
import kotlinx.serialization.Serializable

@Serializable
data class DriveStreamRequest(
    val limit: Int = 10,
    val sinceId: String,
    val untilId: String,
    val type: String
)

typealias DriveStreamResponse = List<DriveFile>
