package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesUnmutePostIdRequest(
    val id: String
)

typealias StatusesUnmutePostIdResponse = Status
