package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.Reaction

class MisskeyReaction(name: String, url: String?) : Reaction(name, url) {
    fun toLocal(): MisskeyReaction = MisskeyReaction(
        name.replace(":", "").split("@").first(),
        url
    )

}
