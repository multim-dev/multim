package dev.usbharu.multim.model.common.impl.misskey

import dev.usbharu.multim.model.common.StatusContent

class MisskeyContent(val content: String) : StatusContent() {
    override fun getText(): String {
        return content
    }
}
