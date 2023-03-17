package dev.usbharu.multim.misskey.v13.model

import kotlinx.serialization.Serializable

@Serializable
data class DriveFoldersDeleteRequest(
    val folderId: String
)
