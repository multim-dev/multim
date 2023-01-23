package dev.usbharu.multim.model.misskey.v12.components

import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val id: String,
    val aliases: List<String>,
    val name: String,
    val category: String?,
    val host: String?,
    val url: String
)
