package dev.usbharu.multim.multi

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.model.*
import dev.usbharu.multim.multi.model.MultiAccountAccount
import dev.usbharu.multim.multi.model.MultiAccountProfile
import dev.usbharu.multim.multi.model.MultiAccountStatus
import dev.usbharu.multim.multi.model.MultiAccountStatusId

class MultiAccountAccountApi(private val multiAccountApiBase: MultiAccountApiBase) : AccountApi {

    override suspend fun userTimeline(
        account: Account,
        since: StatusId?,
        until: StatusId?
    ): List<Status> {
        if (account is MultiAccountAccount && since is MultiAccountStatusId && until is MultiAccountStatusId) {
            return getImpl(account) {
                userTimeline(
                    it,
                    since,
                    until
                )
            }.innerData.map { MultiAccountStatus(it, account.hashCode) }
        } else {
            TODO()
        }
    }

    override suspend fun follow(account: Account): Boolean {
        return getImpl2(account) { follow(it) }.first
    }

    suspend fun follow(account: MultiAccountData<Account>): MultiAccountData<Boolean> {
        return getImpl(account) { follow(it) }
    }


    override suspend fun unfollow(account: Account): Boolean {
        return getImpl2(account) { unfollow(it) }.first
    }

    override suspend fun profile(account: Account): Profile {
        return MultiAccountProfile(getImpl2(account) { profile(it) })
    }

    override suspend fun statuses(account: Account, includeRepost: Boolean): List<Status> {
        TODO("Not yet implemented")
    }

    override suspend fun relationships(myself: Account, other: Account): Relation {
        TODO("Not yet implemented")
    }

    override suspend fun requestCancel(account: Account): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun requestAccept(account: Account): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun requestReject(account: Account): Boolean {
        TODO("Not yet implemented")
    }

    suspend fun unfollow(account: MultiAccountData<Account>): MultiAccountData<Boolean> {
        return getImpl(account) { unfollow(it) }
    }

    suspend fun profile(account: MultiAccountData<Account>): MultiAccountData<Profile> {
        return getImpl(account) { profile(it) }
    }

    suspend fun statuses(account: MultiAccountData<Account>): MultiAccountData<List<Status>> {
        return getImpl(account) { statuses(it) }
    }

    suspend fun relationships(
        myself: MultiAccountData<Account>,
        other: MultiAccountData<Account>
    ): MultiAccountData<Relation> {
        return getImpl(myself) { relationships(it, other.innerData) }
    }

    suspend fun requestCancel(account: MultiAccountData<Account>): MultiAccountData<Boolean> {
        return getImpl(account) { requestCancel(it) }
    }

    suspend fun requestAccept(account: MultiAccountData<Account>): MultiAccountData<Boolean> {
        return getImpl(account) { requestAccept(it) }
    }

    suspend fun requestReject(account: MultiAccountData<Account>): MultiAccountData<Boolean> {
        return getImpl(account) { requestReject(it) }
    }

    private suspend fun <T, R> getImpl(
        apiData: MultiAccountData<T>,
        callback: suspend AccountApi.(T) -> R
    ): MultiAccountData<R> =
        MultiAccountDataImpl(callback(accountApi(apiData), apiData.innerData), apiData.hashCode)

    private suspend fun <T, R> getImpl2(
        apiData: T,
        callback: suspend AccountApi.(T) -> R
    ): Pair<R, Int> {
        return callback(accountApi(apiData), apiData) to (
                (apiData as? MultiAccountData<*>)?.hashCode
                    ?: multiAccountApiBase.mainClientHashCode!!)
    }

    private fun <T> accountApi(id: T): AccountApi {
        return multiAccountApiBase.getImpl((id as? MultiAccountData<*>)?.hashCode).accountApi
    }
}
