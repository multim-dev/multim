package dev.usbharu.multim.multi

import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountTimelineApi(private val multiAccountApiBase: MultiAccountApiBase) {

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


    private fun timelineApi(id: MultiAccountData<*>) =
        multiAccountApiBase.getImpl(id.hashCode).timelineApi

}
