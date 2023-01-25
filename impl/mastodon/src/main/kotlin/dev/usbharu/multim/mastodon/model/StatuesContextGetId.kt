package dev.usbharu.multim.mastodon.model

import dev.usbharu.multim.mastodon.model.components.Context

data class StatuesContextGetIdRequest(
    val id: String
)

typealias StatuesContextGetIdResponse = Context
