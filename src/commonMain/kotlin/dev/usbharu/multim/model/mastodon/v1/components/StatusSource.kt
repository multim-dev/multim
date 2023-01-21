package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.serialization.Serializable

@Serializable
data class StatusSource(
    val id: String,
    val text: String
)
