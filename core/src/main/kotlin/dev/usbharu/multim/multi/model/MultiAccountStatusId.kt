package dev.usbharu.multim.multi.model

import dev.usbharu.multim.model.StatusId
import dev.usbharu.multim.multi.MultiAccountData

class MultiAccountStatusId(override val innerData: StatusId, override val hashCode: Int) :
    MultiAccountData<StatusId>,
    StatusId() {
    override fun equals(other: Any?): Boolean {
        return innerData == other
    }

    override fun hashCode(): Int {
        return innerData.hashCode()
    }

    override fun getUrl(): String {
        return innerData.getUrl()
    }

    override val cacheKey: String
        get() = innerData.cacheKey
}
