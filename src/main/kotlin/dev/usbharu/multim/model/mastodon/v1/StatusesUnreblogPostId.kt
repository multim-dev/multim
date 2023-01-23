package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesUnreblogPostIdRequest(
    val id: String
)

typealias StatusesUnreblogPostIdResponse = Status
