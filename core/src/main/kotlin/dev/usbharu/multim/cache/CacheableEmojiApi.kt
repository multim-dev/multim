package dev.usbharu.multim.cache

import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Emoji

class CacheableEmojiApi(val cacheableApi: CacheableApi, val emojiApi: EmojiApi) :
    CacheableApi by cacheableApi, EmojiApi by emojiApi {
    override suspend fun get(name: String): MultiMResult<Emoji> {
        return cacheableApi.cacheOrGet(GET, name) { emojiApi.get(name) }
    }

    override suspend fun findByName(name: String): MultiMResult<List<Emoji>> {
        return cacheableApi.cacheOrGet(FIND_BY_NAME, name) { emojiApi.findByName(name) }
    }
}
