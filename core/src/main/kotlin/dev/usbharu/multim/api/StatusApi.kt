package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import com.goncalossilva.murmurhash.MurmurHash3
import dev.usbharu.multim.Logger
import dev.usbharu.multim.UniqueId
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

interface StatusApi {
    val POST:String
        get() = "status/post"
    suspend fun post(status: StatusForPost): Result<Status,MultiMError>{
        Logger.debug("Status Api","Not impl status api post")
        return Err(MultiMError("post not implements",null, ErrorType.NOT_IMPL))
    }

    val DELETE:String
        get() = "status/delete"
    suspend fun delete(id: StatusId): Result<Unit,MultiMError>{
        Logger.debug("Status Api","Not impl status api delete")
        return Err(MultiMError("delete not implements",null,ErrorType.NOT_IMPL))
    }
    val FIND_BY_ID:String
        get() = "status/findById"
    suspend fun findById(id: StatusId): Result<Status,MultiMError> {
        Logger.debug("Status Api","Not impl status api findById")
        return Err(MultiMError("findById not implements",null,ErrorType.NOT_IMPL))
    }
    val ADD_REACTION:String
        get() = "status/addReaction"
    suspend fun addReaction(id: StatusId, reaction: Reaction): Result<Unit,MultiMError> {
        Logger.debug("Status Api","Not impl status api addReaction")
        return Err(MultiMError("addReaction not implements",null,ErrorType.NOT_IMPL))
    }

    val REMOVE_REACTION:String
        get() = "status/remvoeReaction"
    /**
     * Remove reaction
     *
     * @param id
     * @param reaction 実装によって挙動が変わります。nullでも
     * @return
     */
    suspend fun removeReaction(id: StatusId, reaction: Reaction?): Result<Unit,MultiMError> {
        Logger.debug("Status Api","Not impl status api removeReaction")
        return Err(MultiMError("removeReaction not implements",null,ErrorType.NOT_IMPL))
    }
    val REACTIONS:String
        get() = "status/reactions"
    suspend fun reactions(id: StatusId): Result<Map<Reaction, Int>,MultiMError>{
        Logger.debug("Status Api","Not impl status api reactions")
        return Err(MultiMError("reactions not implements",null,ErrorType.NOT_IMPL))
    }
    val REPLIES:String
        get() = "status/replies"
    suspend fun replies(id: StatusId): Result<List<Status>,MultiMError>{
        Logger.debug("Status Api","Not impl status api replies")
        return Err(MultiMError("replies not implements",null,ErrorType.NOT_IMPL))
    }
    val REPOST:String
        get() = "status/repost"
    suspend fun repost(id: StatusId): Result<Status,MultiMError> {
        Logger.debug("Status Api","Not impl status api repost")
        return Err(MultiMError("repost not implements",null,ErrorType.NOT_IMPL))
    }
    val UN_REPOST:String
        get() = "status/unRepost"
    suspend fun unRepost(id: StatusId): Result<Unit,MultiMError>{
        Logger.debug("Status Api","Not impl status api unRepost")
        return Err(MultiMError("unRepost not implements",null,ErrorType.NOT_IMPL))
    }
    val REPLY_TO:String
        get() = "status/replyTo"
    suspend fun replyTo(id: StatusId, status: StatusForPost): Result<Status,MultiMError> {
        Logger.debug("Status Api","Not impl status api replyTo")
        return Err(MultiMError("replyTo not implements",null,ErrorType.NOT_IMPL))
    }
    val ADD_TO_BOOKMARKS:String
        get() = "status/addBookMarks"
    suspend fun addToBookmarks(id: StatusId): Result<Unit,MultiMError>{
        Logger.debug("Status Api","Not impl status api addToBookmarks")
        return Err(MultiMError("addToBookmarks not implements",null,ErrorType.NOT_IMPL))
    }
    val REMOVE_FROM_BOOKMARKS:String
        get() = "status/removeFromBookmarks"
    suspend fun removeFromBookmarks(id: StatusId): Result<Unit,MultiMError>{
        Logger.debug("Status Api","Not impl status api removeFromBookmarks")
        return Err(MultiMError("removeFromBookmarks not implements",null,ErrorType.NOT_IMPL))
    }
    val GET_PREVIOUS_AND_NEXT:String
        get() = "status/getPreviousAndNext"
    suspend fun getPreviousAndNext(id: StatusId): Result<PreviousAndNextPosts,MultiMError>{
        Logger.debug("Status Api","Not impl status api getPreviousAndNext")
        return Err(MultiMError("getPreviousAndNext not implements",null,ErrorType.NOT_IMPL))
    }

    fun getUniqueId(status: Status):Int{
        val localDateTime = status.createdAt.toLocalDateTime(TimeZone.UTC)
        val date:String =
            "${localDateTime.year}${localDateTime.monthNumber}${localDateTime.dayOfMonth}${localDateTime.hour}${localDateTime.minute}${localDateTime.second}"
        return UniqueId.hashAlgorithm.hash32x86(
            (status.content.text + status.account.accountName + date).encodeToByteArray()
        ).toInt()
    }
}


object NotImplStatusApi : StatusApi
