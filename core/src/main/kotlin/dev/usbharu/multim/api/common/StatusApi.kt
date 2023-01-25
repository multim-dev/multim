package dev.usbharu.multim.api.common

import dev.usbharu.multim.model.common.*

interface StatusApi {
    suspend fun post(status: StatusForPost): Status
    suspend fun delete(id: StatusId): Boolean
    suspend fun findById(id: StatusId): Status
    suspend fun addReaction(id: StatusId, reaction: Reaction): Boolean


    /**
     * Remove reaction
     *
     * @param id
     * @param reaction 実装によって挙動が変わります。nullでも
     * @return
     */
    suspend fun removeReaction(id: StatusId, reaction: Reaction?): Boolean
    suspend fun reactions(id: StatusId): Map<Reaction, Int>
    suspend fun replies(id: StatusId): List<Status>
    suspend fun repost(id: StatusId): Status
    suspend fun unRepost(id: StatusId): Boolean
    suspend fun replyTo(id: StatusId, status: StatusForPost): Status
    suspend fun addToBookmarks(id: StatusId): Boolean
    suspend fun removeFromBookmarks(id: StatusId): Boolean
    suspend fun getPreviousAndNext(id: StatusId): PreviousAndNextPosts
}
