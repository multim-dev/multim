package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Emoji

interface EmojiApi {
    val GET:String
        get() = "emoji/get"
    suspend fun get(name:String):MultiMResult<Emoji>{
        Logger.debug("Emoji Api","Not impl emoji api get $name")
        return Err(MultiMError("emoji get not implements",null, ErrorType.NOT_IMPL))
    }

    val FIND_BY_NAME:String
        get() = "emoji/findByName"

    suspend fun findByName(name:String):MultiMResult<List<Emoji>>{
        Logger.debug("Emoji Api","Not impl emoji api findByName $name")
        return Err(MultiMError("emoji findByName not implements",null,ErrorType.NOT_IMPL))
    }
}

object NotImplEmojiApi : EmojiApi
