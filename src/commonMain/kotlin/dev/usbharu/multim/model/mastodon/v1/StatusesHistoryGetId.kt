package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.StatusEdit
import kotlinx.serialization.Serializable

@Serializable
data class StatusesHistoryGetIdRequest(
    val id: String
)

typealias StatusesHistoryGetIdResponse = List<StatusEdit>
