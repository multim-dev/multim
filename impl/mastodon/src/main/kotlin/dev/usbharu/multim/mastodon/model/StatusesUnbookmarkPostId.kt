package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesUnbookmarkPostIdRequest(
    val id: String
)

typealias StatusesUnbookmarkPostIdResponse = Status
