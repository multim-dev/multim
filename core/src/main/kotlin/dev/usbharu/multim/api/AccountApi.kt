package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.*

//todo 成功したかをboolで返しているが、詳細がわからないのでしっかり返す。

interface AccountApi {
    suspend fun userTimeline(
        account: Account,
        since: StatusId? = null,
        until: StatusId? = null
    ): Result<List<Status>,MultiMError>

    suspend fun follow(account: Account): Result<Unit,MultiMError>
    suspend fun unfollow(account: Account): Result<Unit,MultiMError>
    suspend fun profile(account: Account): Result<Profile,MultiMError>
    suspend fun statuses(account: Account, includeRepost: Boolean = false): Result<List<Status>,MultiMError>
    suspend fun relationships(myself: Account, other: Account): Result<Relation,MultiMError>
    suspend fun requestCancel(account: Account): Result<Unit,MultiMError>
    suspend fun requestAccept(account: Account): Result<Unit,MultiMError>
    suspend fun requestReject(account: Account): Result<Unit,MultiMError>
}

object NotImplAccountApi : AccountApi {
    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): Result<List<Status>, MultiMError> {
        return Err(MultiMError("userTimeline not implements", null, ErrorType.NOT_IMPL))
    }

    override suspend fun follow(account: Account): Result<Unit, MultiMError> {
        return Err(MultiMError("follow not implements", null, ErrorType.NOT_IMPL))
    }

    override suspend fun unfollow(account: Account): Result<Unit, MultiMError> {
        return Err(MultiMError("unfollow not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun profile(account: Account): Result<Profile, MultiMError> {
        return Err(MultiMError("profile not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun statuses(
        account: Account,
        includeRepost: Boolean
    ): Result<List<Status>, MultiMError> {
        return Err(MultiMError("statuses not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun relationships(
        myself: Account,
        other: Account
    ): Result<Relation, MultiMError> {
        return Err(MultiMError("relationships not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun requestCancel(account: Account): Result<Unit, MultiMError> {
        return Err(MultiMError("requestCancel not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun requestAccept(account: Account): Result<Unit, MultiMError> {
        return Err(MultiMError("requestAccept not implements",null,ErrorType.NOT_IMPL))
    }

    override suspend fun requestReject(account: Account): Result<Unit, MultiMError> {
        return Err(MultiMError("requestReject not implements",null,ErrorType.NOT_IMPL))
    }
}
