package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.StatusSource
import kotlinx.serialization.Serializable

@Serializable
data class StatusesSourceGetIdRequest(
    val id: String,
)

typealias StatusesSourceGetIdResponse = StatusSource
