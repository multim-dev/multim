package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Emoji

/**
 * Emoji api.
 * 絵文字の情報を取得するAPI
 *
 */
@Suppress("PropertyName","VariableNaming")
interface EmojiApi {
    val GET: String
        get() = "emoji/get"

    /**
     * 指定した名前から絵文字を取得する.
     * 同じ名前で重複した絵文字があった場合、どの絵文字が取得されるかは実装に依存する.
     *
     * @param name 取得する絵文字の名前
     * @return 取得した絵文字
     */
    suspend fun get(name: String): MultiMResult<Emoji> {
        Logger.debug("Emoji Api", "Not impl emoji api get $name")
        return Err(MultiMError("emoji get not implements", null, ErrorType.NOT_IMPL))
    }

    val FIND_BY_NAME: String
        get() = "emoji/findByName"

    /**
     * 指定した名前から絵文字を検索する.
     * 検索する方法は実装に依存する.
     *
     * @param name 検索する絵文字の名前
     * @return 検索結果の絵文字List
     */
    suspend fun findByName(name: String): MultiMResult<List<Emoji>> {
        Logger.debug("Emoji Api", "Not impl emoji api findByName $name")
        return Err(MultiMError("emoji findByName not implements", null, ErrorType.NOT_IMPL))
    }
}

/**
 * 未実装API
 *
 * すべてのAPIが実装されていないAPI
 */
object NotImplEmojiApi : EmojiApi
