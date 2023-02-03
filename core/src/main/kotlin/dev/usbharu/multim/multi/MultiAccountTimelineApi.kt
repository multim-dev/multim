package dev.usbharu.multim.multi

import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountTimelineApi(private val multiAccountApiBase: MultiAccountApiBase) : TimelineApi {

    suspend fun availableTimelines(id: MultiAccountData<*>): MultiAccountData<List<Timeline>> {
        return getImpl(id) { availableTimelines() }
    }

    suspend fun availableTimelines(hashCode: Int): MultiAccountData<List<Timeline>> {
        return MultiAccountDataImpl<List<Timeline>>(
            multiAccountApiBase.getImpl(hashCode).timelineApi.availableTimelines(),
            hashCode
        )
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun listen(
        timeline: MultiAccountData<Timeline>,
        callback: MultiAccountData<(List<MultiAccountStatus>) -> Unit>
    ): MultiAccountData<Unit> {

        return getImpl(timeline) { listen(it, callback.innerData as (List<Status>) -> Unit) }
    }

    suspend fun connect(timeline: MultiAccountData<Timeline>): MultiAccountData<Boolean> {
        return getImpl(timeline) { connect(it) }
    }

    suspend fun disconnect(
        timeline: MultiAccountData<Timeline>,
        force: Boolean = false
    ): MultiAccountData<Boolean> {
        return getImpl(timeline) { disconnect(it, force) }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend TimelineApi.(T) -> R
    ): MultiAccountData<R> =
        MultiAccountDataImpl(callback(timelineApi(apiData), apiData.innerData), apiData.hashCode)


    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend TimelineApi.(T) -> R
    ): Pair<R, Int> {
        return callback(timelineApi(apiData), apiData) to (
                (apiData as? MultiAccountData<*>)?.hashCode
                    ?: multiAccountApiBase.mainClientHashCode!!)
    }

    private fun <T> timelineApi(id: T) =
        multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode).timelineApi

    override suspend fun availableTimelines(): List<Timeline> {
        TODO("Not yet implemented")
    }

    override suspend fun listen(timeline: Timeline, callback: (List<Status>) -> Unit) {
        return getImpl2(timeline) { listen(it, callback) }.first
    }

    override suspend fun connect(timeline: Timeline): Boolean {
        return getImpl2(timeline){connect(it)}.first
    }

    override suspend fun disconnect(timeline: Timeline, force: Boolean): Boolean {
        return getImpl2(timeline){disconnect(it,force)}.first
    }

}
