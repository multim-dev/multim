package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesReblogPostIdRequest(
    val id: String,
    val visibility: String? = null
)

typealias StatusesReblogPostIdResponse = Status
