package dev.usbharu.multim.api

import com.github.michaelbull.result.Result
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
