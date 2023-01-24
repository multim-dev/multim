package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.StatusSource
import kotlinx.serialization.Serializable

@Serializable
data class StatusesSourceGetIdRequest(
    val id: String,
)

typealias StatusesSourceGetIdResponse = StatusSource
