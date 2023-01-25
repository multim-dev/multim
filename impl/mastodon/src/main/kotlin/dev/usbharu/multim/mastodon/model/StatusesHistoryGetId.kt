package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.StatusEdit
import kotlinx.serialization.Serializable

@Serializable
data class StatusesHistoryGetIdRequest(
    val id: String
)

typealias StatusesHistoryGetIdResponse = List<StatusEdit>
