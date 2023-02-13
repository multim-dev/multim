package dev.usbharu.multim.multi

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.api.createHttpClient
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.ServiceInfoFactory

class MultiAccountApiBase(val serviceList: List<ServiceInfo>) {

    val factory = ServiceInfoFactory(serviceList)

    val httpClient = createHttpClient()

    val apiClientMap = mutableMapOf<Int, MultiMApis>()

    var mainClientHashCode: Int? = null
    suspend fun addAccount(url: String, token: String): Int {
        val hashCode = (url + token).hashCode()
        apiClientMap[hashCode] =
            MultiM.createClient(url, token, factory, httpClient)
        if (mainClientHashCode == null) {
            mainClientHashCode = hashCode
        }
        return hashCode
    }

    suspend fun addMainAccount(url: String, token: String): Int {
        mainClientHashCode =
            addAccount(url, token)
        return mainClientHashCode as Int
    }

    fun getImpl(hashCode: Int? = mainClientHashCode): MultiMApis {
        if (hashCode == null) {
            return apiClientMap[mainClientHashCode]!!
        }
        return apiClientMap[hashCode]!!
    }
}
