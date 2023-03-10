package dev.usbharu.multim.multi

import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.Logger
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.*
import dev.usbharu.multim.multi.model.MultiAccountReaction
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountStatusApi(private val multiAccountApiBase: MultiAccountApiBase) : StatusApi {
    suspend fun post(status: MultiAccountData<StatusForPost>): MultiMResult<MultiAccountData<Status>> {
        Logger.debug("Status Api", "Multi account status api post with MultiAccountData")
        return getImpl(status) { post(it) }
    }

    suspend fun delete(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Unit>> {
        Logger.debug("Status Api", "Multi account status api delete with MultiAccountData")
        return getImpl(id) { delete(it) }
    }

    suspend fun findById(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Status>> {
        Logger.debug("Status Api", "Multi account status api findById with MultiAccountData")
        return getImpl(id) { findById(it) }
    }

    suspend fun addReaction(
        id: MultiAccountData<StatusId>, reaction: MultiAccountData<Reaction>
    ): MultiMResult<MultiAccountData<Unit>> {
        Logger.debug("Status Api", "Multi account status api addReaction with MultiAccountData")
        return getImpl(id) { addReaction(it, reaction.innerData) }
    }

    suspend fun removeReaction(
        id: MultiAccountData<StatusId>, reaction: MultiAccountData<Reaction>
    ): MultiMResult<MultiAccountData<Unit>> {
        Logger.debug("Status Api", "Multi account status api removeReaction with MultiAccountData")
        return getImpl(id) { removeReaction(it, reaction.innerData) }
    }

    suspend fun reactions(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Map<Reaction, Int>>> {
        Logger.debug("Status Api", "Multi account status api reactions with MultiAccountData")
        return getImpl(id) { reactions(it) }
    }

    suspend fun replies(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<List<Status>>> {
        Logger.debug("Status Api", "Multi account status api replies with MultiAccountData")
        return getImpl(id) { replies(it) }
    }

    suspend fun repost(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Status>> {
        Logger.debug("Status Api", "Multi account status api repost with MultiAccountData")
        return getImpl(id) { repost(it) }
    }

    suspend fun unRepost(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Unit>> {
        Logger.debug("Status Api", "Multi account status api unRepost with MultiAccountData")
        return getImpl(id) { unRepost(it) }
    }

    suspend fun replyTo(
        id: MultiAccountData<StatusId>, status: MultiAccountData<StatusForPost>
    ): MultiMResult<MultiAccountData<Status>> {
        Logger.debug("Status Api", "Multi account status api replyTo with MultiAccountData")
        return getImpl(id) { replyTo(it, status.innerData) }
    }

    suspend fun addToBookmarks(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Unit>> {
        Logger.debug("Status Api", "Multi account status api addToBookmarks with MultiAccountData")
        return getImpl(id) { addToBookmarks(it) }
    }

    suspend fun removeFromBookmarks(id: MultiAccountData<StatusId>): MultiMResult<MultiAccountData<Unit>> {
        Logger.debug(
            "Status Api", "Multi account status api removeFromBookmarks with MultiAccountData"
        )
        return getImpl(id) { removeFromBookmarks(it) }
    }

    suspend fun getPreviousAndNext(id: MultiAccountData<StatusId>)
            : MultiMResult<MultiAccountData<PreviousAndNextPosts>> {
        Logger.debug(
            "Status Api", "Multi account status api getPreviousAndNext with MultiAccountData"
        )
        return getImpl(id) { getPreviousAndNext(it) }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>, callback: suspend StatusApi.(T) -> MultiMResult<R>
    ): MultiMResult<MultiAccountData<R>> {
        return statusApi(apiData).flatMap { callback(it, apiData.innerData) }
            .map { MultiAccountDataImpl(it, apiData.hashCode) }
    }

    @Suppress("UnsafeCallOnNullableType")
    private suspend fun <T, R> getImpl2(
        apiData: T, callback: suspend StatusApi.(T) -> R
    ): MultiMResult<Pair<R, Int>> {
        return statusApi(apiData).map {
            callback(
                it, apiData
            ) to ((apiData as? MultiAccountData<*>)?.hashCode ?: multiAccountApiBase.mainClientHashCode!!)
        }
    }

    private fun <T> statusApi(id: T): MultiMResult<StatusApi> {
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode).map { it.statusApi }
    }


    override suspend fun post(status: StatusForPost): MultiMResult<Status> {
        Logger.debug("Status Api", "Multi account status api post")
        return getImpl2(status) { post(it) }.flatMap {
            it.first.map { iStatus ->
                (iStatus to it.second).toMultiAccount()
            }
        }

    }

    override suspend fun delete(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Multi account status api delete")
        return getImpl2(id) { delete(it) }.map { it.first }
    }

    override suspend fun findById(id: StatusId): MultiMResult<Status> {
        Logger.debug("Status Api", "Multi account status api findById")
        return getImpl2(id) { findById(it) }
            .flatMap {
                it.first.map { iStatus -> (iStatus to it.second).toMultiAccount() }
            }
    }

    override suspend fun addReaction(id: StatusId, reaction: Reaction): MultiMResult<Unit> {
        Logger.debug("Status Api", "Multi account status api addReaction")
        return getImpl2(id) { addReaction(it, reaction) }.map { it.first }
    }

    override suspend fun removeReaction(
        id: StatusId, reaction: Reaction?
    ): MultiMResult<Unit> {
        Logger.debug("Status Api", "Multi account status api removeReaction")
        return getImpl2(id) { removeReaction(it, reaction) }.map { it.first }
    }

    override suspend fun reactions(id: StatusId): MultiMResult<Map<Reaction, Int>> {
        Logger.debug("Status Api", "Multi account status api reactions")
        return getImpl2(id) { reactions(it) }.let {
            it.flatMap { resultIntPair ->
                resultIntPair.first.map { map ->
                    map.map { (key, value) ->
                        MultiAccountReaction(
                            key, resultIntPair.second
                        ) to value
                    }.toMap()
                }
            }
        }
    }

    override suspend fun replies(id: StatusId): MultiMResult<List<Status>> {
        Logger.debug("Status Api", "Multi account status api replies")
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

    override suspend fun repost(id: StatusId): MultiMResult<Status> {
        Logger.debug("Status Api", "Multi account status api repost")
        return getImpl2(id) { repost(it) }
            .flatMap {
                it.first.map { iStatus ->
                    (iStatus to it.second).toMultiAccount()
                }
            }
    }

    override suspend fun unRepost(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Multi account status api unRepost")
        return getImpl2(id) { unRepost(it) }.map { it.first }
    }

    override suspend fun replyTo(id: StatusId, status: StatusForPost): MultiMResult<Status> {
        Logger.debug("Status Api", "Multi account status api replyTo")
        return getImpl2(status) {
            replyTo(
                id, it
            )
        }.flatMap { it.first.map { iStatus -> (iStatus to it.second).toMultiAccount() } }
    }

    override suspend fun addToBookmarks(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Multi account status api addToBookmarks")
        return getImpl2(id) { addToBookmarks(it) }.map { it.first }
    }

    override suspend fun removeFromBookmarks(id: StatusId): MultiMResult<Unit> {
        Logger.debug("Status Api", "Multi account status api removeFromBookmarks")
        return getImpl2(id) { removeFromBookmarks(it) }.map { it.first }
    }

    override suspend fun getPreviousAndNext(id: StatusId): MultiMResult<PreviousAndNextPosts> {
        Logger.debug("Status Api", "Multi account status api getPreviousAndNext")
        return getImpl2(id) { getPreviousAndNext(it) }.flatMap { it.first }
    }

    private fun Pair<Status, Int>.toMultiAccount(): MultiAccountStatus {
        return MultiAccountStatus(first, second)
    }

    suspend fun availableReactions(hashCode: Int): MultiMResult<List<Reaction>> {
        Logger.debug("Status Api", "Multi account status api availableReactions")
        return multiAccountApiBase.getImpl(hashCode).flatMap { it.statusApi.availableReactions() }
    }

    suspend fun availableReactions(hashCode: MultiAccountData<*>): MultiMResult<List<Reaction>> {
        Logger.debug(
            "Status Api", "Multi account status api availableReactions with multiAccountData"
        )
        return getImpl2(hashCode) { availableReactions() }.flatMap { it.first }
    }
}
