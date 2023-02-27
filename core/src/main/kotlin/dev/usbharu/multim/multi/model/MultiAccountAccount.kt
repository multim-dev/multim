package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.Account
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountAccount(
    override val innerData: Account,
    override val hashCode: Int
) :
    MultiAccountData<Account>, Account(
    innerData.screenName,
    innerData.accountName,
    innerData.isBot,
    innerData.avatar
) {
    override val cacheKey: String
        get() = innerData.cacheKey
}
