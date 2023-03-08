package dev.usbharu.multim.cache

import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.PreviousAndNextPosts
import dev.usbharu.multim.model.Reaction
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.StatusId

class CacheableStatusApi(private val cacheableApi: CacheableApi, private val statusApi: StatusApi) :
    CacheableApi by cacheableApi, StatusApi by statusApi {
    override suspend fun findById(id: StatusId): MultiMResult<Status> {
        return cacheableApi.cacheOrGet(FIND_BY_ID, id) { statusApi.findById(id) }
    }

    override suspend fun reactions(id: StatusId): MultiMResult<Map<Reaction, Int>> {
        return cacheableApi.cacheOrGet(REACTIONS, id) { statusApi.reactions(id) }
    }

    override suspend fun replies(id: StatusId): MultiMResult<List<Status>> {
        return cacheableApi.cacheOrGet(REPLIES, id) { statusApi.replies(id) }
    }

    override suspend fun getPreviousAndNext(id: StatusId): MultiMResult<PreviousAndNextPosts> {
        return cacheableApi.cacheOrGet(
            GET_PREVIOUS_AND_NEXT, id
        ) { statusApi.getPreviousAndNext(id) }
    }

    override suspend fun availableReactions(): MultiMResult<List<Reaction>> {
        return cacheableApi.cacheOrGet(
            (AVAILABLE_REACTIONS)
        ) {
            statusApi.availableReactions()
        }
    }
}
