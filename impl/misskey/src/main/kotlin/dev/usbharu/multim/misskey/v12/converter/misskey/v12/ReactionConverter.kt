package dev.usbharu.multim.misskey.v12.converter.misskey.v12

import com.github.michaelbull.result.getOrElse
import com.github.michaelbull.result.map
import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.misskey.v12.common.MisskeyReaction
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.NoteReaction
import dev.usbharu.multim.misskey.v12.model.components.Reactions
import dev.usbharu.multim.model.Emoji
import dev.usbharu.multim.model.Reaction

object ReactionConverter {
    fun NoteReaction.toReaction(note: Note): Reaction {
        val split = type.replace(":", "").split("@")
        return MisskeyReaction(
            split[0],
            note.emojis.find { emojiLite -> emojiLite.name == type }?.url
        )
    }

    fun Reactions.toReactions(note: Note): Map<Reaction, Int> {
        return map {
            MisskeyReaction(
                it.key,
                note.emojis.find { emojiLite -> emojiLite.name == it.key }?.url
            ) to it.value
        }.toMap()
    }

    suspend fun Reactions.toReactions(emojiApi: EmojiApi): Map<MisskeyReaction, Int> {
        return map { entries ->
            emojiApi.get(entries.key)
                .map { emoji: Emoji ->
                    MisskeyReaction(emoji.name, emoji.url)
                }.map { misskeyReaction ->
                    misskeyReaction to entries.value
                }
        }.map {
            it.getOrElse { null }
        }.filterNotNull().toMap()
    }
}
