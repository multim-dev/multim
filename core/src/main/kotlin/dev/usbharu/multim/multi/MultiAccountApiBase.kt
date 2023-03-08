package dev.usbharu.multim.multi

import com.github.michaelbull.result.map
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.github.michaelbull.result.toResultOr
import dev.usbharu.multim.Logger
import dev.usbharu.multim.MultiM
import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.error.ErrorType
import dev.usbharu.multim.error.MultiMError
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.ServiceInfoFactory
import kotlin.collections.List
import kotlin.collections.get
import kotlin.collections.mutableMapOf
import kotlin.collections.set

class MultiAccountApiBase(val serviceList: List<ServiceInfo>) {

    val factory = ServiceInfoFactory(serviceList)

    val httpClient = MultiM.httpClientWithJson.config {}

    val apiClientMap = mutableMapOf<Int, MultiMApis>()

    var mainClientHashCode: Int? = null
    suspend fun addAccount(url: String, token: String): MultiMResult<Int> {
        Logger.info("Multi Account", "START Add account url:$url token:${"*".repeat(token.length)}")

        val hashCode = (url + token).hashCode()

        Logger.debug("Multi Account", "Account hashCode : $hashCode")

        val result = MultiM.createClient(url, token, factory, httpClient).map {
            apiClientMap[hashCode] = it
        }.map {
            if (mainClientHashCode == null) {
                mainClientHashCode = hashCode

                Logger.debug(
                    "Multi Account",
                    "Main account is null! This account is set as the main account."
                )

            }
            hashCode
        }
        result.onFailure {
            Logger.error(
                "Multi Account",
                "FAILURE Add account url:$url token:${"*".repeat(token.length)}",
                it
            )
        }
        result.onSuccess {
            Logger.info("Multi Account", "SUCCESS Add account url:$url")
        }
        return result
    }

    suspend fun addMainAccount(url: String, token: String): MultiMResult<Int> {
        Logger.info(
            "Multi Account",
            "Add account url:$url token:${"*".repeat(token.length)} as main account."
        )
        return addAccount(url, token).map { mainClientHashCode = it;it }
    }

    fun getImpl(hashCode: Int? = mainClientHashCode): MultiMResult<MultiMApis> {
        Logger.debug("Multi Account", "Get api client impl $hashCode")
        if (hashCode == null) {
            Logger.warn("Multi Account", "HashCode is null! Use main account.")
            return apiClientMap[mainClientHashCode].toResultOr {
                Logger.warn("Multi Account", "Main account client not found!")
                MultiMError(
                    "Main Client not found : $mainClientHashCode",
                    null,
                    ErrorType.ILLEGAL_ARGUMENT
                )
            }
        }
        return apiClientMap[hashCode].toResultOr {
            Logger.warn("Multi Account", "Api client not found. $hashCode")
            MultiMError("Api Client not found : $hashCode", null, ErrorType.ILLEGAL_ARGUMENT)
        }
    }
}
