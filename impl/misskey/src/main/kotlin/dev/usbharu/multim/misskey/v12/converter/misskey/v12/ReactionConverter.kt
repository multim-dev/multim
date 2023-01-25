package dev.usbharu.multim.misskey.v12.converter.misskey.v12

import dev.usbharu.multim.misskey.v12.common.MisskeyReaction
import dev.usbharu.multim.misskey.v12.model.components.Note
import dev.usbharu.multim.misskey.v12.model.components.NoteReaction
import dev.usbharu.multim.misskey.v12.model.components.Reactions
import dev.usbharu.multim.model.common.Reaction

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
}
