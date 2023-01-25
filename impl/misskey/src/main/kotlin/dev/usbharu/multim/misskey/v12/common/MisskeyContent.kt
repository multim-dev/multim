package dev.usbharu.multim.misskey.v12.common

import dev.usbharu.multim.model.StatusContent

class MisskeyContent(val content: String) : StatusContent() {
    override fun getText(): String {
        return content
    }
}
