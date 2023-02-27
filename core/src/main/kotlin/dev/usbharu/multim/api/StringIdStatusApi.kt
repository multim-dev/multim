package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.PreviousAndNextPosts
import dev.usbharu.multim.model.Reaction
import dev.usbharu.multim.model.Status

interface StringIdStatusApi : StatusApi {
    suspend fun findById(statusId: String): MultiMResult<Status> {
        Logger.debug("Status Api", "Not impl status api findById by id")
        return Err(MultiMError("findById by id not implements", null, ErrorType.NOT_IMPL))
    }

    suspend fun reactions(statusId: String): MultiMResult<Map<Reaction, Int>> {
        Logger.debug("Status Api", "Not impl status api reactions by id")
        return Err(MultiMError("reactions by id not implements", null, ErrorType.NOT_IMPL))
    }

    suspend fun replies(statusId: String): MultiMResult<List<Status>> {
        Logger.debug("Status Api", "Not impl status api replies by id")
        return Err(MultiMError("replies by id not implements", null, ErrorType.NOT_IMPL))
    }

    suspend fun getPreviousAndNext(statusId: String): MultiMResult<PreviousAndNextPosts> {
        Logger.debug("Status Api", "Not impl status api getPreviousAndNext by id")
        return Err(MultiMError("getPreviousAndNext by id not implements", null, ErrorType.NOT_IMPL))
    }
}


object NotImplStringIdStatusApi : StringIdStatusApi
