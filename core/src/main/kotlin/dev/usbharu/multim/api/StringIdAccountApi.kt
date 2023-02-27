package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.*

interface StringIdAccountApi : AccountApi {
    suspend fun userTimeline(
        accountId: String,
        since: String? = null,
        until: String? = null,
    ): MultiMResult<List<Status>>{
        Logger.debug("Account Api","Not impl account api. userTimeline by id.")
        return Err(MultiMError("userTimeline by id not implements", null, ErrorType.NOT_IMPL))
    }

    suspend fun statuses(
        accountId: String,
        includeRepost: Boolean = false
    ): MultiMResult<List<Status>>{
        Logger.debug("Account Api","Not impl account api. statuses by id.")
        return Err(MultiMError("statuses by id not implements",null, ErrorType.NOT_IMPL))
    }

    suspend fun profile(accountId: String): MultiMResult<Profile> {
        Logger.debug("Account Api","Not impl account api. profile by id.")
        return Err(MultiMError("profile by id not implements",null, ErrorType.NOT_IMPL))
    }
}

object NotImplStringIdAccountApi : StringIdAccountApi
