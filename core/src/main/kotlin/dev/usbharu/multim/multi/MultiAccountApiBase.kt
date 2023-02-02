package dev.usbharu.multim.multi

import dev.usbharu.multim.MultiM
import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.api.createHttpClient
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.ServiceInfoFactory
import java.security.MessageDigest

class MultiAccountApiBase(private val serviceList: List<ServiceInfo>) {

    val factory = ServiceInfoFactory(serviceList)

    val httpClient = createHttpClient()

    val hashAlgorithm = MessageDigest.getInstance("SHA-1")

    val apiClientMap = mutableMapOf<Int, MultiMApis>()
    suspend fun addAccount(url: String, token: String): Int {
        val hashCode = (url + token).hashCode()
        apiClientMap[hashCode] =
            MultiM.createClient(url, token, factory, httpClient)
        return hashCode
    }

    fun getImpl(hashCode:Int):MultiMApis{
        return apiClientMap.getValue(hashCode)
    }
}
