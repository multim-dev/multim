package dev.usbharu.multim.misskey.v12.model

import dev.usbharu.multim.misskey.v12.model.components.MisskeyNeedAuth
import dev.usbharu.multim.misskey.v12.model.components.Note
import kotlinx.serialization.Serializable

@Serializable
data class DriveFilesAttachedNotesRequest(
    val fileId:String
) : MisskeyNeedAuth()

typealias DriveFilesAttachedNotesResponse = List<Note>
