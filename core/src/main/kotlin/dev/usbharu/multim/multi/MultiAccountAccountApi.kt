package dev.usbharu.multim.multi

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.model.*

class MultiAccountAccountApi(private val multiAccountApiBase: MultiAccountApiBase) {

    suspend fun userTimeline(
        account: MultiAccountData<Account>,
        since: MultiAccountData<StatusId?>? = null,
        until: MultiAccountData<StatusId?>? = null
    ): MultiAccountData<List<Status>> {
        return getImpl(account) { userTimeline(it, since?.data, until?.data) }
    }

    suspend fun follow(account: MultiAccountData<Account>): MultiAccountData<Boolean> {
        return getImpl(account) { follow(it) }
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
        return getImpl(myself) { relationships(it, other.data) }
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
        MultiAccountDataImpl(callback(accountApi(apiData), apiData.data), apiData.hashCode)


    private fun accountApi(id: MultiAccountData<*>) =
        multiAccountApiBase.getImpl(id.hashCode).accountApi
}
