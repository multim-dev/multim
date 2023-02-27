package dev.usbharu.multim.cache

import com.github.michaelbull.result.Result
import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.Timeline

class CacheableTimelineApi(private val cacheableApi: CacheableApi,private val timelineApi: TimelineApi) :
    CacheableApi by cacheableApi, TimelineApi by timelineApi {
    override suspend fun availableTimelines(): Result<List<Timeline>, MultiMError> {
        return cacheableApi.cacheOrGet(AVAILABLE_TIMELINES) { timelineApi.availableTimelines() }
    }
}
