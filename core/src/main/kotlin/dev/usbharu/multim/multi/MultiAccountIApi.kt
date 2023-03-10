package dev.usbharu.multim.multi

import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.Logger
import dev.usbharu.multim.api.IApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.model.Status

class MultiAccountIApi(val multiAccountApiBase: MultiAccountApiBase) : IApi {
    override suspend fun profile(): MultiMResult<Profile> {
        TODO("Not yet implemented")
    }

    override suspend fun statuses(): MultiMResult<List<Status>> {
        TODO("Not yet implemented")
    }

    suspend fun profile(hashCode: Int): MultiMResult<Profile> {
        Logger.debug("I Api", "Multi account i api iApi with MultiAccountData")
        return multiAccountApiBase.getImpl(hashCode).flatMap { it.i.profile() }
    }

    suspend fun statuses(hashCode: Int): MultiMResult<List<Status>> {
        Logger.debug("I Api", "Multi account i api iApi with MultiAccountData")
        return multiAccountApiBase.getImpl(hashCode).flatMap { it.i.statuses() }
    }

    suspend fun profile(hashCode: MultiAccountData<*>): MultiMResult<Profile> {
        Logger.debug("I Api", "Multi account i api iApi with MultiAccountData")
        return getImpl2(hashCode) { profile() }.flatMap { it.first }
    }

    suspend fun statuses(hashCode: MultiAccountData<*>): MultiMResult<List<Status>> {
        Logger.debug("I Api", "Multi account i api iApi with MultiAccountData")
        return getImpl2(hashCode) { statuses() }.flatMap { it.first }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend IApi.(T) -> MultiMResult<R>
    ): MultiMResult<MultiAccountDataImpl<R>> {
        return iApi(apiData)
            .flatMap { callback(it, apiData.innerData) }
            .map { MultiAccountDataImpl(it, apiData.hashCode) }
    }

    @Suppress("UnsafeCallOnNullableType")
    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend IApi.(T) -> R
    ): MultiMResult<Pair<R, Int>> {
        val timelineApi = iApi(apiData)
        return timelineApi.map {
            callback(it, apiData) to (
                    (apiData as? MultiAccountData<*>)?.hashCode
                        ?: multiAccountApiBase.mainClientHashCode!!)
        }
    }

    private fun <T> iApi(id: T): MultiMResult<IApi> {
        Logger.debug("I Api", "Multi account timeline api timelineApi with MultiAccountData")
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode)
            .map { it.i }
    }
}
