package dev.usbharu.multim.multi

import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.Logger
import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Emoji

class MultiAccountEmojiApi(val multiAccountApiBase: MultiAccountApiBase) : EmojiApi {
    override suspend fun get(name: String): MultiMResult<Emoji> {
        Logger.debug("Emoji Api", "Multi account emoji api get $name")
        return getImpl2(name) { get(it) }.flatMap { it.first }
    }

    override suspend fun findByName(name: String): MultiMResult<List<Emoji>> {
        Logger.debug("Emoji Api", "Multi account emoji api findByName $name")
        return getImpl2(name) { findByName(it) }.flatMap { it.first }
    }

    suspend fun get(name: MultiAccountData<String>): MultiMResult<MultiAccountData<Emoji>> {
        Logger.debug("Emoji Api", "Multi account emoji api get $name with MultiAccountData")
        return getImpl(name) { get(it) }
    }

    suspend fun findByName(name: MultiAccountData<String>): MultiMResult<MultiAccountData<List<Emoji>>> {
        Logger.debug("Emoji Api", "Multi account emoji api findByName $name with MultiAccountData")
        return getImpl(name) { findByName(it) }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend EmojiApi.(T) -> MultiMResult<R>
    ): MultiMResult<MultiAccountData<R>> {
        return emojiApi(apiData)
            .flatMap { callback(it, apiData.innerData) }
            .map { MultiAccountDataImpl(it, apiData.hashCode) }
    }

    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend EmojiApi.(T) -> R
    ): MultiMResult<Pair<R, Int>> {
        return emojiApi(apiData)
            .map { callback(it, apiData) }
            .map {
                it to ((apiData as? MultiAccountData<*>)?.hashCode
                    ?: multiAccountApiBase.mainClientHashCode!!)
            }
    }

    private fun <T> emojiApi(id: T): MultiMResult<EmojiApi> {
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode)
            .map { it.emojiApi }
    }
}
