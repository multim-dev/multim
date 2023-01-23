package dev.usbharu.multim.api.common

import dev.usbharu.multim.model.common.*

//todo 成功したかをboolで返しているが、詳細がわからないのでしっかり返す。

interface AccountApi {
    suspend fun userTimeline(
        account: Account,
        since: StatusId? = null,
        until: StatusId? = null
    ): List<Status>

    suspend fun follow(account: Account): Boolean
    suspend fun unfollow(account: Account): Boolean
    suspend fun profile(account: Account): Profile
    suspend fun statuses(account: Account, includeRepost: Boolean = false): List<Status>
    suspend fun relationships(myself: Account, other: Account): Relation
    suspend fun requestCancel(account: Account): Boolean
    suspend fun requestAccept(account: Account): Boolean
    suspend fun requestReject(account: Account): Boolean
}
