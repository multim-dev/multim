package dev.usbharu.multim.cache

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.model.*

class CacheableAccountApi(
    private val accountApi: AccountApi,
    private val cacheableApi: CacheableApi
) :
    CacheableApi by cacheableApi, AccountApi by accountApi {
    @Deprecated("statusesに統合")
    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): MultiMResult<List<Status>> {
        return cacheableApi.cacheOrGet(
            CacheableApi.generateKey(
                account,
                since,
                until
            )
        ) { accountApi.userTimeline(account, since, until) }
    }

    override suspend fun profile(account: Account): MultiMResult<Profile> {
        return cacheableApi.cacheOrGet(PROFILE, account) { accountApi.profile(account) }
    }

    override suspend fun statuses(
        account: Account,
        includeRepost: Boolean,
        since: StatusId?,
        until: StatusId?
    ): MultiMResult<List<Status>> {
        return cacheableApi.cacheOrGet(
            STATUSES,
            CacheableApi.generateKey(account) + includeRepost
        ) {
            accountApi.statuses(
                account,
                includeRepost,
                since, until
            )
        }
    }

    override suspend fun relationships(
        myself: Account,
        other: Account
    ): MultiMResult<Relation> {
        return cacheableApi.cacheOrGet(RELATIONSHIPS, myself, other) {
            accountApi.relationships(
                myself,
                other
            )
        }
    }
}
