package dev.usbharu.multim.mastodon.model.components

import kotlinx.serialization.Serializable

@Serializable
data class Context(
    val ancestors: List<Status>,
    val descendants: List<Status>
)
