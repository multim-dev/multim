package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.usbharu.multim.Logger
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.*

//todo 成功したかをboolで返しているが、詳細がわからないのでしっかり返す。

interface AccountApi {
    @Deprecated("statusesに統合")
    suspend fun userTimeline(
        account: Account,
        since: StatusId? = null,
        until: StatusId? = null
    ): Result<List<Status>, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. userTimeline.")
        return Err(MultiMError("userTimeline not implements", null, ErrorType.NOT_IMPL))
    }

    val FOLLOW: String
        get() = "account/follow"

    suspend fun follow(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. follow.")
        return Err(MultiMError("follow not implements", null, ErrorType.NOT_IMPL))
    }

    val UNFOLLOW: String
        get() = "account/unfollow"

    suspend fun unfollow(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. unfollow.")
        return Err(MultiMError("unfollow not implements", null, ErrorType.NOT_IMPL))
    }

    val PROFILE: String
        get() = "account/profile"

    suspend fun profile(account: Account): Result<Profile, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. profile.")
        return Err(MultiMError("profile not implements", null, ErrorType.NOT_IMPL))
    }

    val STATUSES: String
        get() = "account/statuses"

    suspend fun statuses(
        account: Account,
        includeRepost: Boolean = false,
        since:StatusId? = null,
        until:StatusId? = null,
    ): Result<List<Status>, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. statuses.")
        return Err(MultiMError("statuses not implements", null, ErrorType.NOT_IMPL))
    }

    val RELEATIONSHIPS: String
        get() = "account/relationships"

    suspend fun relationships(myself: Account, other: Account): Result<Relation, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. relationships.")
        return Err(MultiMError("relationships not implements", null, ErrorType.NOT_IMPL))
    }

    val REQUEST_CANCEL: String
        get() = "account/requestCancel"

    suspend fun requestCancel(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. requestCancel.")
        return Err(MultiMError("requestCancel not implements", null, ErrorType.NOT_IMPL))
    }

    val REQUEST_ACCEPT: String
        get() = "account/requestAccept"

    suspend fun requestAccept(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. requestAccept.")
        return Err(MultiMError("requestAccept not implements", null, ErrorType.NOT_IMPL))
    }

    val REQUEST_REJECT: String
        get() = "account/requestReject"

    suspend fun requestReject(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api", "Not impl account api. requestReject.")
        return Err(MultiMError("requestReject not implements", null, ErrorType.NOT_IMPL))
    }
}

object NotImplAccountApi : AccountApi
