package dev.usbharu.multim.multi

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import com.github.michaelbull.result.toResultOr
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.api.createHttpClient
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.ServiceInfoFactory

class MultiAccountApiBase(val serviceList: List<ServiceInfo>) {

    val factory = ServiceInfoFactory(serviceList)

    val httpClient = createHttpClient()

    val apiClientMap = mutableMapOf<Int, MultiMApis>()

    var mainClientHashCode: Int? = null
    suspend fun addAccount(url: String, token: String): Result<Int, MultiMError> {
        val hashCode = (url + token).hashCode()
        return MultiM.createClient(url, token, factory, httpClient).map {
            apiClientMap[hashCode] = it
        }.map {
            if (mainClientHashCode == null) {
                mainClientHashCode = hashCode
            }
            hashCode
        }
    }

    suspend fun addMainAccount(url: String, token: String): Result<Int, MultiMError> {

        return addAccount(url, token).map { mainClientHashCode = it;it }
    }

    fun getImpl(hashCode: Int? = mainClientHashCode): Result<MultiMApis,MultiMError> {
        if (hashCode == null) {
            return apiClientMap[mainClientHashCode].toResultOr {
                MultiMError("Main Client not found : $mainClientHashCode",null, ErrorType.ILLEGAL_ARGUMENT)
            }
        }
        return apiClientMap[hashCode].toResultOr {
            MultiMError("Api Client not found : $hashCode",null,ErrorType.ILLEGAL_ARGUMENT)
        }
    }
}
