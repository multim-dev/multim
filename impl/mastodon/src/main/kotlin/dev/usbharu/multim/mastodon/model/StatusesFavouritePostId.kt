package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatusesFavouritePostIdRequest(
    val id: String
)

typealias StatusesFavouritePostIdResponse = Status
