package dev.usbharu.multim.mastodon.model.components

import kotlinx.serialization.Serializable

@Serializable
data class StatusSource(
    val id: String,
    val text: String
)
