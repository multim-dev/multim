package dev.usbharu.multim.converter.misskey.v12

import dev.usbharu.multim.model.common.Reaction
import dev.usbharu.multim.model.common.impl.misskey.MisskeyReaction
import dev.usbharu.multim.model.misskey.v12.components.Note
import dev.usbharu.multim.model.misskey.v12.components.NoteReaction
import dev.usbharu.multim.model.misskey.v12.components.Reactions

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
