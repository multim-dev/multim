package dev.usbharu.multim.util

import Emojis
import dev.usbharu.multim.model.Emoji


object EmojiUtils {
    fun getAllEmoji():List<UnicodeEmoji>{
        val result = mutableListOf<UnicodeEmoji>()
        result.addAll(Emojis.Activities.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.AnimalsAndNature.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.Component.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.Flags.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.FoodAndDrink.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.Objects.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.PeopleAndBody.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.SmileysAndEmotion.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.TravelAndPlaces.values().map { UnicodeEmoji(it.description,it.char) })
        result.addAll(Emojis.Symbols.values().map { UnicodeEmoji(it.description,it.char) })
        return result
    }
}

data class UnicodeEmoji(val string: String,val char:String)
