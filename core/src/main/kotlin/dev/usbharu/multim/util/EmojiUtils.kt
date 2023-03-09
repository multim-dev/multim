package dev.usbharu.multim.util

import Emojis


object EmojiUtils {
    fun getAllEmoji():List<UnicodeEmoji>{
        return Emojis.allEmojis.map { UnicodeEmoji(it.description,it.char) }
    }
}

data class UnicodeEmoji(val string: String,val char:String)
