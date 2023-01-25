package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Status
import kotlinx.serialization.Serializable

@Serializable
data class StatuesDeleteIdRequest(
    val id: String
)


//todo textとpollとmediaattachmentsを追加する https://docs.joinmastodon.org/methods/statuses/#delete
typealias StatuesDeleteIdResponse = Status
