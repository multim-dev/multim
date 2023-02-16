package dev.usbharu.multim.api

import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Emoji

interface EmojiApi {
    suspend fun get(name:String):MultiMResult<Emoji>

    suspend fun findByName(name:String):MultiMResult<List<Emoji>>
}
