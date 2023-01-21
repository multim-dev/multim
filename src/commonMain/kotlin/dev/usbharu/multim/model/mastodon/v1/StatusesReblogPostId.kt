package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesReblogPostIdRequest(
    val id: String,
    val visibility: String? = null
)

typealias StatusesReblogPostIdResponse = Status
