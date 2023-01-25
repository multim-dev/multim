package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFolder
import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersShowRequest(
    val folderId:String
)

typealias DriveFoldersShowResponse = DriveFolder
