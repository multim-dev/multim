package dev.usbharu.multim.cache

import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Timeline

class CacheableTimelineApi(
    private val cacheableApi: CacheableApi,
    private val timelineApi: TimelineApi
) :
    CacheableApi by cacheableApi, TimelineApi by timelineApi {
    override suspend fun availableTimelines(): MultiMResult<List<Timeline>> {
        return cacheableApi.cacheOrGet(AVAILABLE_TIMELINES) { timelineApi.availableTimelines() }
    }
}
