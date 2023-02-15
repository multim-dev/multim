package dev.usbharu.multim.multi

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.*
import dev.usbharu.multim.multi.model.MultiAccountReaction
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountStatusApi(private val multiAccountApiBase: MultiAccountApiBase) : StatusApi {
    suspend fun post(status: MultiAccountData<StatusForPost>): Result<MultiAccountData<Status>, MultiMError> =
        getImpl(status) { post(it) }

    suspend fun delete(id: MultiAccountData<StatusId>): Result<MultiAccountData<Unit>, MultiMError> =
        getImpl(id) { delete(it) }

    suspend fun findById(id: MultiAccountData<StatusId>): Result<MultiAccountData<Status>, MultiMError> =
        getImpl(id) { findById(it) }

    suspend fun addReaction(
        id: MultiAccountData<StatusId>, reaction: MultiAccountData<Reaction>
    ): Result<MultiAccountData<Unit>, MultiMError> =
        getImpl(id) { addReaction(it, reaction.innerData) }

    suspend fun removeReaction(
        id: MultiAccountData<StatusId>, reaction: MultiAccountData<Reaction>
    ): Result<MultiAccountData<Unit>, MultiMError> =
        getImpl(id) { removeReaction(it, reaction.innerData) }

    suspend fun reactions(id: MultiAccountData<StatusId>): Result<MultiAccountData<Map<Reaction, Int>>, MultiMError> =
        getImpl(id) { reactions(it) }

    suspend fun replies(id: MultiAccountData<StatusId>): Result<MultiAccountData<List<Status>>, MultiMError> =
        getImpl(id) { replies(it) }

    suspend fun repost(id: MultiAccountData<StatusId>): Result<MultiAccountData<Status>, MultiMError> =
        getImpl(id) { repost(it) }

    suspend fun unRepost(id: MultiAccountData<StatusId>): Result<MultiAccountData<Unit>, MultiMError> =
        getImpl(id) { unRepost(it) }

    suspend fun replyTo(
        id: MultiAccountData<StatusId>, status: MultiAccountData<StatusForPost>
    ): Result<MultiAccountData<Status>, MultiMError> = getImpl(id) { replyTo(it, status.innerData) }

    suspend fun addToBookmarks(id: MultiAccountData<StatusId>): Result<MultiAccountData<Unit>, MultiMError> =
        getImpl(id) { addToBookmarks(it) }

    suspend fun removeFromBookmarks(id: MultiAccountData<StatusId>): Result<MultiAccountData<Unit>, MultiMError> =
        getImpl(id) { removeFromBookmarks(it) }

    suspend fun getPreviousAndNext(id: MultiAccountData<StatusId>): Result<MultiAccountData<PreviousAndNextPosts>, MultiMError> =
        getImpl(id) { getPreviousAndNext(it) }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>, callback: suspend StatusApi.(T) -> Result<R, MultiMError>
    ): Result<MultiAccountData<R>, MultiMError> {
        return statusApi(apiData).flatMap { callback(it, apiData.innerData) }
            .map { MultiAccountDataImpl(it, apiData.hashCode) }
    }

    private suspend fun <T, R> getImpl2(
        apiData: T, callback: suspend StatusApi.(T) -> R
    ): Result<Pair<R, Int>, MultiMError> {
        return statusApi(apiData).map {
            callback(
                it, apiData
            ) to ((apiData as? MultiAccountData<*>)?.hashCode
                ?: multiAccountApiBase.mainClientHashCode!!)
        }
    }

    private fun <T> statusApi(id: T): Result<StatusApi, MultiMError> {
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode)
            .map { it.statusApi }
    }


    override suspend fun post(status: StatusForPost): Result<Status, MultiMError> {
        return getImpl2(status) { post(it) }.flatMap {
            it.first.map { iStatus ->
                (iStatus to it.second).toMultiAccount()
            }
        }

    }

    override suspend fun delete(id: StatusId): Result<Unit, MultiMError> {
        return getImpl2(id) { delete(it) }.map { it.first }
    }

    override suspend fun findById(id: StatusId): Result<Status, MultiMError> {
        return getImpl2(id) { findById(it) }.flatMap { it.first.map { iStatus -> (iStatus to it.second).toMultiAccount() } }
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): Result<Unit, MultiMError> {
        return getImpl2(id) { addReaction(it, reaction) }.map { it.first }
    }

    override suspend fun removeReaction(
        id: StatusId, reaction: Reaction?
    ): Result<Unit, MultiMError> {
        return getImpl2(id) { removeReaction(it, reaction) }.map { it.first }
    }

    override suspend fun reactions(id: StatusId): Result<Map<Reaction, Int>, MultiMError> {
        return getImpl2(id) { reactions(it) }.let {
            it.flatMap {
                it.first.map { map ->
                    map.map { (key, value) ->
                        MultiAccountReaction(
                            key, it.second
                        ) to value
                    }.toMap()
                }
            }
        }
    }

    override suspend fun replies(id: StatusId): Result<List<Status>, MultiMError> {
        return getImpl2(id) { replies(it) }.flatMap {
            it.first.map { statusList ->
                statusList.map { status ->
                    MultiAccountStatus(
                        status, it.second
                    )
                }
            }
        }
    }

    override suspend fun repost(id: StatusId): Result<Status, MultiMError> {
        return getImpl2(id) { repost(it) }.flatMap { it.first.map { iStatus -> (iStatus to it.second).toMultiAccount() } }
    }

    override suspend fun unRepost(id: StatusId): Result<Unit, MultiMError> {
        return getImpl2(id) { unRepost(it) }.map { it.first }
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): Result<Status, MultiMError> {
        return getImpl2(status) {
            replyTo(
                id, it
            )
        }.flatMap { it.first.map { iStatus -> (iStatus to it.second).toMultiAccount() } }
    }

    override suspend fun addToBookmarks(id: StatusId): Result<Unit, MultiMError> {
        return getImpl2(id) { addToBookmarks(it) }.map { it.first }
    }

    override suspend fun removeFromBookmarks(id: StatusId): Result<Unit, MultiMError> {
        return getImpl2(id) { removeFromBookmarks(it) }.map { it.first }
    }

    override suspend fun getPreviousAndNext(id: StatusId): Result<PreviousAndNextPosts, MultiMError> {
        TODO("Not yet implemented")
    }

    private fun Pair<Status, Int>.toMultiAccount(): MultiAccountStatus {
        return MultiAccountStatus(first, second)
    }


}
