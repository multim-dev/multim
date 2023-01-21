package dev.usbharu.multim.model.mastodon.v1

import dev.usbharu.multim.model.mastodon.v1.components.Context

data class StatuesContextGetIdRequest(
    val id: String
)

typealias StatuesContextGetIdResponse = Context
