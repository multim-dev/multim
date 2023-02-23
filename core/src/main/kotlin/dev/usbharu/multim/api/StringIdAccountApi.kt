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
    ): MultiMResult<List<Status>>

    suspend fun statuses(
        accountId: String,
        includeRepost: Boolean = false
    ): MultiMResult<List<Status>>

    suspend fun profile(accountId: String): MultiMResult<Profile>
}

object NotImplStringIdAccountApi : StringIdAccountApi {
    override suspend fun userTimeline(
        accountId: String,
        since: String?,
        until: String?
    ): MultiMResult<List<Status>> {
        Logger.debug("Account Api","Not impl account api. userTimeline by id.")
        return Err(MultiMError("userTimeline by id not implements", null, ErrorType.NOT_IMPL))
    }

    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): Result<List<Status>, MultiMError> {
        Logger.debug("Account Api","Not impl account api. userTimeline.")
        return Err(MultiMError("userTimeline not implements", null, ErrorType.NOT_IMPL))
    }

    override suspend fun statuses(
        accountId: String,
        includeRepost: Boolean
    ): MultiMResult<List<Status>> {
        Logger.debug("Account Api","Not impl account api. statuses by id.")
        return Err(MultiMError("statuses by id not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun follow(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Not impl account api. follow.")
        return Err(MultiMError("follow not implements", null, ErrorType.NOT_IMPL))
    }

    override suspend fun unfollow(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Not impl account api. unfollow.")
        return Err(MultiMError("unfollow not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun profile(account: Account): Result<Profile, MultiMError> {
        Logger.debug("Account Api","Not impl account api. profile.")
        return Err(MultiMError("profile not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun statuses(
        account: Account,
        includeRepost: Boolean
    ): Result<List<Status>, MultiMError> {
        Logger.debug("Account Api","Not impl account api. statuses.")
        return Err(MultiMError("statuses not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun profile(accountId: String): MultiMResult<Profile> {
        Logger.debug("Account Api","Not impl account api. profile by id.")
        return Err(MultiMError("profile by id not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun relationships(
        myself: Account,
        other: Account
    ): Result<Relation, MultiMError> {
        Logger.debug("Account Api","Not impl account api. relationships.")
        return Err(MultiMError("relationships not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun requestCancel(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Not impl account api. requestCancel.")
        return Err(MultiMError("requestCancel not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun requestAccept(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Not impl account api. requestAccept.")
        return Err(MultiMError("requestAccept not implements",null, ErrorType.NOT_IMPL))
    }

    override suspend fun requestReject(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Not impl account api. requestReject.")
        return Err(MultiMError("requestReject not implements",null, ErrorType.NOT_IMPL))
    }
}
