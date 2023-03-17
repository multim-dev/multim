package dev.usbharu.multim.misskey.v13.common

import dev.usbharu.multim.misskey.v13.model.StreamRequest
import dev.usbharu.multim.model.Timeline

data class MisskeyTimeline(
    val id: String,
    override val name: String,
    val channel: StreamRequest.ConnectRequest.Body.Channel
) : Timeline(name)
