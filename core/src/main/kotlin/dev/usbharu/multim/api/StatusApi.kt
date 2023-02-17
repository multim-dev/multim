package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.*

interface StatusApi {
    suspend fun post(status: StatusForPost): Result<Status,MultiMError>
    suspend fun delete(id: StatusId): Result<Unit,MultiMError>
    suspend fun findById(id: StatusId): Result<Status,MultiMError>
    suspend fun addReaction(id: StatusId, reaction: Reaction): Result<Unit,MultiMError>


    /**
     * Remove reaction
     *
     * @param id
     * @param reaction 実装によって挙動が変わります。nullでも
     * @return
     */
    suspend fun removeReaction(id: StatusId, reaction: Reaction?): Result<Unit,MultiMError>
    suspend fun reactions(id: StatusId): Result<Map<Reaction, Int>,MultiMError>
    suspend fun replies(id: StatusId): Result<List<Status>,MultiMError>
    suspend fun repost(id: StatusId): Result<Status,MultiMError>
    suspend fun unRepost(id: StatusId): Result<Unit,MultiMError>
    suspend fun replyTo(id: StatusId, status: StatusForPost): Result<Status,MultiMError>
    suspend fun addToBookmarks(id: StatusId): Result<Unit,MultiMError>
    suspend fun removeFromBookmarks(id: StatusId): Result<Unit,MultiMError>
    suspend fun getPreviousAndNext(id: StatusId): Result<PreviousAndNextPosts,MultiMError>
}


object NotImplStatusApi : StatusApi {
    override suspend fun post(status: StatusForPost): Result<Status, MultiMError> {
        return Err(MultiMError("post not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun delete(id: StatusId): Result<Unit, MultiMError> {
        return Err(MultiMError("delete not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun findById(id: StatusId): Result<Status, MultiMError> {
        return Err(MultiMError("findById not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): Result<Unit, MultiMError> {
        return Err(MultiMError("addReaction not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun removeReaction(
        id: StatusId,
        reaction: Reaction?
    ): Result<Unit, MultiMError> {
        return Err(MultiMError("removeReaction not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun reactions(id: StatusId): Result<Map<Reaction, Int>, MultiMError> {
        return Err(MultiMError("reactions not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun replies(id: StatusId): Result<List<Status>, MultiMError> {
        return Err(MultiMError("replies not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun repost(id: StatusId): Result<Status, MultiMError> {
        return Err(MultiMError("repost not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun unRepost(id: StatusId): Result<Unit, MultiMError> {
        return Err(MultiMError("unRepost not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): Result<Status, MultiMError> {
        return Err(MultiMError("replyTo not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun addToBookmarks(id: StatusId): Result<Unit, MultiMError> {
        return Err(MultiMError("addToBookmarks not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun removeFromBookmarks(id: StatusId): Result<Unit, MultiMError> {
        return Err(MultiMError("removeFromBookmarks not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun getPreviousAndNext(id: StatusId): Result<PreviousAndNextPosts, MultiMError> {
        return Err(MultiMError("getPreviousAndNext not implements",null,ErrorType.NOT_IMPL))
    }
}
