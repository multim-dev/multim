package dev.usbharu.multim.multi

import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.model.*

class MultiAccountStatusApi(private val multiAccountApiBase: MultiAccountApiBase) {
    suspend fun post(status: MultiAccountData<StatusForPost>): MultiAccountData<Status> =
        getImpl(status) { post(it) }

    suspend fun delete(id: MultiAccountData<StatusId>): MultiAccountData<Boolean> =
        getImpl(id) { delete(it) }

    suspend fun findById(id: MultiAccountData<StatusId>): MultiAccountData<Status> =
        getImpl(id) { findById(it) }

    suspend fun addReaction(
        id: MultiAccountData<StatusId>,
        reaction: MultiAccountData<Reaction>
    ): MultiAccountData<Boolean> = getImpl(id) { addReaction(it, reaction.innerData) }

    suspend fun removeReaction(
        id: MultiAccountData<StatusId>,
        reaction: MultiAccountData<Reaction>
    ): MultiAccountData<Boolean> = getImpl(id) { removeReaction(it, reaction.innerData) }

    suspend fun reactions(id: MultiAccountData<StatusId>): MultiAccountData<Map<Reaction, Int>> =
        getImpl(id) { reactions(it) }

    suspend fun replies(id: MultiAccountData<StatusId>): MultiAccountData<List<Status>> =
        getImpl(id) { replies(it) }

    suspend fun repost(id: MultiAccountData<StatusId>): MultiAccountData<Status> =
        getImpl(id) { repost(it) }

    suspend fun unRepost(id: MultiAccountData<StatusId>): MultiAccountData<Boolean> =
        getImpl(id) { unRepost(it) }

    suspend fun replyTo(
        id: MultiAccountData<StatusId>,
        status: MultiAccountData<StatusForPost>
    ): MultiAccountData<Status> = getImpl(id) { replyTo(it, status.innerData) }

    suspend fun addToBookmarks(id: MultiAccountData<StatusId>): MultiAccountData<Boolean> =
        getImpl(id) { addToBookmarks(it) }

    suspend fun removeFromBookmarks(id: MultiAccountData<StatusId>): MultiAccountData<Boolean> =
        getImpl(id) { removeFromBookmarks(it) }

    suspend fun getPreviousAndNext(id: MultiAccountData<StatusId>): MultiAccountData<PreviousAndNextPosts> =
        getImpl(id) { getPreviousAndNext(it) }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend StatusApi.(T) -> R
    ): MultiAccountData<R> =
        MultiAccountDataImpl(callback(statusApi(apiData), apiData.innerData), apiData.hashCode)


    private fun statusApi(id: MultiAccountData<*>) =
        multiAccountApiBase.getImpl(id.hashCode).statusApi
}
