package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.*

interface StringIdStatusApi : StatusApi {
    suspend fun findById(statusId:String):MultiMResult<Status>

    suspend fun reactions(statusId: String):MultiMResult<Map<Reaction,Int>>
    suspend fun replies(statusId: String):MultiMResult<List<Status>>
    suspend fun getPreviousAndNext(statusId: String):MultiMResult<PreviousAndNextPosts>
}


object NotImplStringIdStatusApi : StringIdStatusApi {
    override suspend fun post(status: StatusForPost): Result<Status, MultiMError> {
        Logger.debug("Status Api","Not impl status api post")
        return Err(MultiMError("post not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun delete(id: StatusId): Result<Unit, MultiMError> {
        Logger.debug("Status Api","Not impl status api delete")
        return Err(MultiMError("delete not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun findById(statusId: String): MultiMResult<Status> {
        Logger.debug("Status Api","Not impl status api findById by id")
        return Err(MultiMError("findById by id not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun findById(id: StatusId): Result<Status, MultiMError> {
        Logger.debug("Status Api","Not impl status api findById")
        return Err(MultiMError("findById not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun reactions(statusId: String): MultiMResult<Map<Reaction, Int>> {
        Logger.debug("Status Api","Not impl status api reactions by id")
        return Err(MultiMError("reactions by id not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): Result<Unit, MultiMError> {
        Logger.debug("Status Api","Not impl status api addReaction")
        return Err(MultiMError("addReaction not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun removeReaction(
        id: StatusId,
        reaction: Reaction?
    ): Result<Unit, MultiMError> {
        Logger.debug("Status Api","Not impl status api removeReaction")
        return Err(MultiMError("removeReaction not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun reactions(id: StatusId): Result<Map<Reaction, Int>, MultiMError> {
        Logger.debug("Status Api","Not impl status api reactions")
        return Err(MultiMError("reactions not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun replies(statusId: String): MultiMResult<List<Status>> {
        Logger.debug("Status Api","Not impl status api replies by id")
        return Err(MultiMError("replies by id not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun replies(id: StatusId): Result<List<Status>, MultiMError> {
        Logger.debug("Status Api","Not impl status api replies")
        return Err(MultiMError("replies not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun getPreviousAndNext(statusId: String): MultiMResult<PreviousAndNextPosts> {
        Logger.debug("Status Api","Not impl status api getPreviousAndNext by id")
        return Err(MultiMError("getPreviousAndNext by id not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun repost(id: StatusId): Result<Status, MultiMError> {
        Logger.debug("Status Api","Not impl status api repost")
        return Err(MultiMError("repost not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun unRepost(id: StatusId): Result<Unit, MultiMError> {
        Logger.debug("Status Api","Not impl status api unRepost")
        return Err(MultiMError("unRepost not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): Result<Status, MultiMError> {
        Logger.debug("Status Api","Not impl status api replyTo")
        return Err(MultiMError("replyTo not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun addToBookmarks(id: StatusId): Result<Unit, MultiMError> {
        Logger.debug("Status Api","Not impl status api addToBookmarks")
        return Err(MultiMError("addToBookmarks not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun removeFromBookmarks(id: StatusId): Result<Unit, MultiMError> {
        Logger.debug("Status Api","Not impl status api removeFromBookmarks")
        return Err(MultiMError("removeFromBookmarks not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun getPreviousAndNext(id: StatusId): Result<PreviousAndNextPosts, MultiMError> {
        Logger.debug("Status Api","Not impl status api getPreviousAndNext")
        return Err(MultiMError("getPreviousAndNext not implements",null, ErrorType.NOT_IMPL))
    }
}
