package dev.usbharu.multim.multi

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.Logger
import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.Timeline
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountTimelineApi(private val multiAccountApiBase: MultiAccountApiBase) : TimelineApi {

    suspend fun availableTimelines(id: MultiAccountData<*>): Result<MultiAccountDataImpl<List<Timeline>>, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api availableTimelines with MultiAccountData")
        return getImpl(id) { availableTimelines() }
    }

    suspend fun availableTimelines(hashCode: Int): Result<MultiAccountData<List<Timeline>>, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api availableTimelines")
        return multiAccountApiBase.getImpl(hashCode)
            .flatMap { it.timelineApi.availableTimelines() }
            .map { MultiAccountDataImpl(it, hashCode) }
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun listen(
        timeline: MultiAccountData<Timeline>,
        callback: MultiAccountData<(List<MultiAccountStatus>) -> Unit>
    ): Result<MultiAccountDataImpl<Unit>, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api listen with MultiAccountData")
        return getImpl(timeline) { listen(it, callback.innerData as (List<Status>) -> Unit) }
    }

    suspend fun connect(timeline: MultiAccountData<Timeline>): Result<MultiAccountDataImpl<Unit>, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api connect with MultiAccountData")
        return getImpl(timeline) { connect(it) }
    }

    suspend fun disconnect(
        timeline: MultiAccountData<Timeline>,
        force: Boolean = false
    ): Result<MultiAccountDataImpl<Unit>, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api disconnect with MultiAccountData")
        return getImpl(timeline) { disconnect(it, force) }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend TimelineApi.(T) -> Result<R, MultiMError>
    ): Result<MultiAccountDataImpl<R>, MultiMError> {
        return timelineApi(apiData)
            .flatMap { callback(it, apiData.innerData) }
            .map { MultiAccountDataImpl(it, apiData.hashCode) }
    }


    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend TimelineApi.(T) -> R
    ): Result<Pair<R, Int>, MultiMError> {
        val timelineApi = timelineApi(apiData)
        return timelineApi.map {
            callback(it, apiData) to (
                    (apiData as? MultiAccountData<*>)?.hashCode
                        ?: multiAccountApiBase.mainClientHashCode!!)
        }
    }

    private fun <T> timelineApi(id: T): Result<TimelineApi, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api timelineApi with MultiAccountData")
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode)
            .map { it.timelineApi }
    }

    override suspend fun availableTimelines(): Result<List<Timeline>, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api availableTimelines")
        TODO("Not yet implemented")
    }

    override suspend fun listen(
        timeline: Timeline,
        callback: (List<Status>) -> Unit
    ): Result<Unit, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api listen")
        return getImpl2(timeline) { listen(it, callback) }.map { it.first }
    }

    override suspend fun connect(timeline: Timeline): Result<Unit, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api connect")
        return getImpl2(timeline) { connect(it) }.map { it.first }
    }

    override suspend fun disconnect(timeline: Timeline, force: Boolean): Result<Unit, MultiMError> {
        Logger.debug("Timeline Api","Multi account timeline api disconnect")
        return getImpl2(timeline) { disconnect(it, force) }.map { it.first }
    }

}
