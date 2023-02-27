package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.Profile
import dev.usbharu.multim.model.Status

interface IApi {
    val PROFILE: String
        get() = "i/profile"

    suspend fun profile(): MultiMResult<Profile>
    val STATUSES: String
        get() = "i/statuses"

    suspend fun statuses(): MultiMResult<List<Status>>

}

object NotImplIApi : IApi {
    override suspend fun profile(): MultiMResult<Profile> {
        Logger.debug("I Api", "Not impl i api. profile")
        return Err(MultiMError("profile not implements", null, ErrorType.NOT_IMPL))
    }

    override suspend fun statuses(): MultiMResult<List<Status>> {
        Logger.debug("I Api", "Not impl i api. statuses")
        return Err(MultiMError("profile not implements", null, ErrorType.NOT_IMPL))
    }

}
