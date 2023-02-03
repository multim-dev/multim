package dev.usbharu.multim.multi

import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.model.*
import dev.usbharu.multim.multi.model.MultiAccountReaction
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountStatusApi(private val multiAccountApiBase: MultiAccountApiBase) : StatusApi {
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

    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend StatusApi.(T) -> R
    ): Pair<R, Int> {
        return callback(statusApi(apiData), apiData) to (
                (apiData as? MultiAccountData<*>)?.hashCode
                    ?: multiAccountApiBase.mainClientHashCode!!
                )
    }

    private fun <T> statusApi(id: T) =
        multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode).statusApi

    override suspend fun post(status: StatusForPost): Status {
        return getImpl2(status) { post(it) }.toMultiAccount()
    }

    override suspend fun delete(id: StatusId): Boolean {
        return getImpl2(id) { delete(it) }.first
    }

    override suspend fun findById(id: StatusId): Status {
        return getImpl2(id) { findById(it) }.toMultiAccount()
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): Boolean {
        return getImpl2(id) { addReaction(it, reaction) }.first
    }

    override suspend fun removeReaction(id: StatusId, reaction: Reaction?): Boolean {
        return getImpl2(id) { removeReaction(it, reaction) }.first
    }

    override suspend fun reactions(id: StatusId): Map<Reaction, Int> {
        return getImpl2(id) { reactions(it) }.let {
            it.first.map { (key, value) ->
                MultiAccountReaction(
                    key,
                    it.second
                ) to value
            }.toMap()
        }
    }

    override suspend fun replies(id: StatusId): List<Status> {
        return getImpl2(id) { replies(it) }.let {
            it.first.map { status ->
                MultiAccountStatus(
                    status,
                    it.second
                )
            }
        }
    }

    override suspend fun repost(id: StatusId): Status {
        return getImpl2(id) { repost(it) }.toMultiAccount()
    }

    override suspend fun unRepost(id: StatusId): Boolean {
        return getImpl2(id) { unRepost(it) }.first
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): Status {
        return getImpl2(status) { replyTo(id, it) }.toMultiAccount()
    }

    override suspend fun addToBookmarks(id: StatusId): Boolean {
        return getImpl2(id) { addToBookmarks(it) }.first
    }

    override suspend fun removeFromBookmarks(id: StatusId): Boolean {
        return getImpl2(id) { removeFromBookmarks(it) }.first
    }

    override suspend fun getPreviousAndNext(id: StatusId): PreviousAndNextPosts {
        TODO("Not yet implemented")
    }

    private fun Pair<Status, Int>.toMultiAccount(): MultiAccountStatus {
        return MultiAccountStatus(first, second)
    }
}
