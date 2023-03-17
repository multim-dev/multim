package dev.usbharu.multim.misskey.v13.common.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.map
import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.misskey.v13.api.MisskeyApis
import dev.usbharu.multim.misskey.v13.common.MisskeyEmoji
import dev.usbharu.multim.misskey.v13.model.MetaRequest
import dev.usbharu.multim.model.Emoji
import dev.usbharu.multim.misskey.v13.model.components.Emoji as MisskeyModelEmoji

class MisskeyEmojiApi(val misskeyApis: MisskeyApis) : EmojiApi {
    override suspend fun get(name: String): MultiMResult<MisskeyEmoji> {
//        return misskeyApis.meta.meta(MetaRequest())
//            .flatMap {
//                it.emojis.find { emoji: MisskeyModelEmoji -> emoji.name.equals(name, true) }
//                    .toResultOr {
//                        MultiMError(
//                            "Emoji $name not Found",
//                            null,
//                            ErrorType.ILLEGAL_ARGUMENT
//                        )
//                    }
//            }.map { MisskeyEmoji(it.name, it.url) }
        return Err(MultiMError("Not Impl",null,ErrorType.NOT_IMPL))
    }

    override suspend fun findByName(name: String): MultiMResult<List<Emoji>> {
//        return misskeyApis.meta.meta(MetaRequest())
//            .map {
//                it.emojis.filter { emoji: MisskeyModelEmoji ->
//                    emoji.name.startsWith(name, true).or(emoji.name.endsWith(name, true))
//                }
//            }.map {
//                it.map { emoji -> MisskeyEmoji(emoji.name, emoji.url) }
//            }
        return Err(MultiMError("Not Impl",null,ErrorType.NOT_IMPL))
    }
}
