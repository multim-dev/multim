package dev.usbharu.multim

import com.github.michaelbull.result.map
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import dev.usbharu.multim.api.NodeinfoApi
import dev.usbharu.multim.error.MultiMResult
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.PlatformApiFactory
import dev.usbharu.multim.model.Auth
import dev.usbharu.multim.multi.MultiAccountApiBase
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object MultiM {


    // todo 認証等を何とかするために、返却するのはAPIをクライアントではなくAPIクライアントのビルダーにする。
    /**
     * URLにアクセスして実装を推測し、自動でAPIクライアントを作成します。.
     * URLからの推測にはwell-known/nodeinfoを使用しています。
     *
     * @param url 使用するURLの末尾にスラッシュを付けてください。
     * @param factory 使用するファクトリークラスのインスタンス。
     * @param token 使用するトークン、実装によって変わる
     * @return 作成されたAPIクライアント
     */
    suspend fun createClient(
        url: String,
        token: String,
        factory: PlatformApiFactory,
        httpClient: HttpClient = httpClientWithJson
    ): MultiMResult<MultiMApis> {
        dev.usbharu.multim.Logger.info("Create Client", "START Create client with url:$url.")
        dev.usbharu.multim.Logger.debug(
            "Create Client",
            "Create client with url:$url token:${"*".repeat(token.length)} factory:${factory::class.java}."
        )
        val result = NodeinfoApi(httpClient).nodeinfo(url)
            .map { nodeInfo -> factory.factory(nodeInfo, httpClient, token, url) }
        result.onSuccess {
            dev.usbharu.multim.Logger.info("Create Client", "SUCCESS Create Client with url:$url")
        }
        result.onFailure {
            dev.usbharu.multim.Logger.error(
                "Create Client",
                "FAILURE Create Client with url:$url token:${"*".repeat(token.length)} factory:${factory::class.java}.",
                it
            )

        }
        return result
    }

    suspend fun createClient(
        url: String,
        auth: Auth,
        factory: PlatformApiFactory,
        httpClient: HttpClient = httpClientWithJson
    ): MultiMResult<MultiMApis> {
        dev.usbharu.multim.Logger.info("Create Client", "START Create cient with url:$url.")
        dev.usbharu.multim.Logger.debug(
            "Create Client",
            "Create client with url:$url auth:${auth::class.simpleName}"
        )
        val result = NodeinfoApi(httpClient).nodeinfo(url)
            .map { nodeInfo -> factory.factory(nodeInfo, httpClient, auth, url) }
        result.onSuccess {
            dev.usbharu.multim.Logger.info("Create Client", "SUCCESS Create Client with url:$url")
        }
        result.onFailure {
            dev.usbharu.multim.Logger.error(
                "Create Client",
                "FAILURE Create Client with url:$url auth:${auth::class.simpleName}",
                it
            )
        }
        return result
    }

    fun createMultiAccountClients(serviceInfoList: List<ServiceInfo> = listOf()): MultiAccountApiBase {
        dev.usbharu.multim.Logger.info(
            "Create Client",
            "START Create multi account client with ${serviceInfoList.size} services."
        )
        val multiAccountApiBase = MultiAccountApiBase(serviceInfoList)
        dev.usbharu.multim.Logger.info(
            "Create Client",
            "SUCCESS Create multi account client with ${serviceInfoList.size} services."
        )
        return multiAccountApiBase
    }

    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient(CIO) {
        expectSuccess = true
    }

    val httpClientWithLogger = httpClient.config {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    dev.usbharu.multim.Logger.debug("HTTP Client", message)
                }
            }
            level = LogLevel.HEADERS
        }
    }

    val httpClientWithJson = httpClientWithLogger.config {
        install(ContentNegotiation) {
            json(json)
        }
    }
}
