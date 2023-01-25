package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatuesGetIdRequest(
    val id: String
)

typealias StatuesGetIdResponse = Status
