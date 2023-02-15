package dev.usbharu.multim.api

import com.github.michaelbull.result.Result
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
