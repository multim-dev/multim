package dev.usbharu.multim.misskey.v13.common

import dev.usbharu.multim.model.StatusId

class MisskeyStatusId(val id: String, private val url: String) : StatusId() {
    override fun equals(other: Any?): Boolean {
        return other?.equals(this) ?: false
    }

    override fun hashCode(): Int {
        return 0
    }

    override fun getUrl(): String {
        return url
    }

    override val cacheKey: String
        get() = id
}
