package dev.usbharu.multim.api

import dev.usbharu.multim.model.Emoji

interface EmojiApi {
    operator fun get(name:String):Emoji

    fun findByName(name:String):List<Emoji>
}
