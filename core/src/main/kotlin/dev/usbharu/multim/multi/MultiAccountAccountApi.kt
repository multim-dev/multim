package dev.usbharu.multim.multi

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import dev.usbharu.multim.Logger
import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.model.*
import dev.usbharu.multim.multi.model.MultiAccountProfile
import dev.usbharu.multim.multi.model.MultiAccountStatus

class MultiAccountAccountApi(private val multiAccountApiBase: MultiAccountApiBase) : AccountApi {

    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): Result<List<Status>, MultiMError> {
        Logger.debug("Account Api","Multi account account api userTimeline")
        return getImpl2(account) { userTimeline(it, since, until) }
            .flatMap {
                it.first.map { statuses ->
                    statuses.map { status ->
                        MultiAccountStatus(
                            status,
                            it.second
                        )
                    }
                }
            }
    }

    override suspend fun follow(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Multi account account api follow")
        return getImpl2(account) { follow(it) }.map { it.first }
    }

    suspend fun follow(account: MultiAccountData<Account>): Result<MultiAccountData<Unit>, MultiMError> {
        Logger.debug("Account Api","Multi account account api follow with MultiAccountData")
        return getImpl(account) { follow(it) }
    }


    override suspend fun unfollow(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Multi account account api unfollow")
        return getImpl2(account) { unfollow(it) }.map { it.first }
    }

    override suspend fun profile(account: Account): Result<Profile, MultiMError> {
        Logger.debug("Account Api","Multi account account api profile")
        return getImpl2(account) { profile(it) }
            .flatMap {
                it.first.map { profile ->
                    MultiAccountProfile(
                        profile to it.second
                    )
                }
            }
    }

    override suspend fun statuses(
        account: Account,
        includeRepost: Boolean
    ): Result<List<Status>, MultiMError> {
        Logger.debug("Account Api","Multi account account api statuses")
        return getImpl2(account) {
            statuses(
                it,
                includeRepost
            )
        }.flatMap {
            it.first.map { statuses ->
                statuses.map { status: Status ->
                    MultiAccountStatus(
                        status,
                        it.second
                    )
                }
            }
        }
    }

    override suspend fun relationships(
        myself: Account,
        other: Account
    ): Result<Relation, MultiMError> {
        Logger.debug("Account Api","Multi account account api relationships")
        return getImpl2(myself) { relationships(it, other) }.flatMap { it.first }
    }

    override suspend fun requestCancel(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Multi account account api requestCancel")
        return getImpl2(account) { requestCancel(it) }.map { it.first }
    }

    override suspend fun requestAccept(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Multi account account api requestAccept")
        return getImpl2(account) { requestAccept(account) }.map { it.first }
    }

    override suspend fun requestReject(account: Account): Result<Unit, MultiMError> {
        Logger.debug("Account Api","Multi account account api requestReject")
        return getImpl2(account) { requestReject(it) }.map { it.first }
    }

    suspend fun unfollow(account: MultiAccountData<Account>): Result<MultiAccountData<Unit>, MultiMError> {
        Logger.debug("Account Api","Multi account account api unfollow with MultiAccountData")
        return getImpl(account) { unfollow(it) }
    }

    suspend fun profile(account: MultiAccountData<Account>): Result<MultiAccountData<Profile>, MultiMError> {
        Logger.debug("Account Api","Multi account account api profile with MultiAccountData")
        return getImpl(account) { profile(it) }
    }

    suspend fun statuses(account: MultiAccountData<Account>): Result<MultiAccountData<List<Status>>, MultiMError> {
        Logger.debug("Account Api","Multi account account api status with MultiAccountData")
        return getImpl(account) { statuses(it) }
    }

    suspend fun relationships(
        myself: MultiAccountData<Account>,
        other: MultiAccountData<Account>
    ): Result<MultiAccountData<Relation>, MultiMError> {
        Logger.debug("Account Api","Multi account account api relationships with MultiAccountData")
        return getImpl(myself) { relationships(it, other.innerData) }
    }

    suspend fun requestCancel(account: MultiAccountData<Account>): Result<MultiAccountData<Unit>, MultiMError> {
        Logger.debug("Account Api","Multi account account api requestCancel with MultiAccountData")
        return getImpl(account) { requestCancel(it) }
    }

    suspend fun requestAccept(account: MultiAccountData<Account>): Result<MultiAccountData<Unit>, MultiMError> {
        Logger.debug("Account Api","Multi account account api requestAccept with MultiAccountData")
        return getImpl(account) { requestAccept(it) }
    }

    suspend fun requestReject(account: MultiAccountData<Account>): Result<MultiAccountData<Unit>, MultiMError> {
        Logger.debug("Account Api","Multi account account api requestAccept with MultiAccountData")
        return getImpl(account) { requestReject(it) }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend AccountApi.(T) -> Result<R, MultiMError>
    ): Result<MultiAccountData<R>, MultiMError> {
        return accountApi(apiData)
            .flatMap { callback(it, apiData.innerData) }
            .map { MultiAccountDataImpl(it, apiData.hashCode) }
    }

    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend AccountApi.(T) -> R
    ): Result<Pair<R, Int>, MultiMError> {
        return accountApi(apiData)
            .map { callback(it, apiData) }
            .map {
                it to ((apiData as? MultiAccountData<*>)?.hashCode
                    ?: multiAccountApiBase.mainClientHashCode!!)
            }
    }

    private fun <T> accountApi(id: T): Result<AccountApi, MultiMError> {
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode)
            .map { it.accountApi }
    }
}
