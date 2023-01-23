package dev.usbharu.multim.model.common.impl.misskey

import dev.usbharu.multim.model.common.Reaction

class MisskeyReaction(name: String, url: String?) : Reaction(name, url) {
    fun toLocal(): MisskeyReaction = MisskeyReaction(
        name.replace(":", "").split("@").first(),
        url
    )

}