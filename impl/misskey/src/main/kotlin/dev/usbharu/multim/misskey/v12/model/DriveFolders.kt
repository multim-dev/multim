package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.DriveFolder
import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersRequest(
    val limit:Int,
    val sinceId:String,
    val untilId:String,
    val folderId:String? = null
)

typealias DriveFoldersResponse = DriveFolder
