package dev.usbharu.multim.cache

import com.github.michaelbull.result.Result
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.PreviousAndNextPosts
import dev.usbharu.multim.model.Reaction
import dev.usbharu.multim.model.Status
import dev.usbharu.multim.model.StatusId

class CacheableStatusApi(private val cacheableApi: CacheableApi,private val statusApi: StatusApi) :
    CacheableApi by cacheableApi, StatusApi by statusApi {
    override suspend fun findById(id: StatusId): Result<Status, MultiMError> {
        return cacheableApi.cacheOrGet(FIND_BY_ID, id) { statusApi.findById(id) }
    }

    override suspend fun reactions(id: StatusId): Result<Map<Reaction, Int>, MultiMError> {
        return cacheableApi.cacheOrGet(REACTIONS, id) { statusApi.reactions(id) }
    }

    override suspend fun replies(id: StatusId): Result<List<Status>, MultiMError> {
        return cacheableApi.cacheOrGet(REPLIES, id) { statusApi.replies(id) }
    }

    override suspend fun getPreviousAndNext(id: StatusId): Result<PreviousAndNextPosts, MultiMError> {
        return cacheableApi.cacheOrGet(
            GET_PREVIOUS_AND_NEXT,
            id
        ) { statusApi.getPreviousAndNext(id) }
    }
}
