package dev.usbharu.multim.model.mastodon.v1.components

import kotlinx.serialization.Serializable

@Serializable
data class Context(
    val ancestors: List<Status>,
    val descendants: List<Status>
)
