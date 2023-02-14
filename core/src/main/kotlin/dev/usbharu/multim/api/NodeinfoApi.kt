package dev.usbharu.multim.api

import com.github.michaelbull.result.*
import dev.usbharu.multim.MultiM.json
import dev.usbharu.multim.error.*
import dev.usbharu.multim.model.wellknown.NodeinfoList
import dev.usbharu.multim.model.wellknown.nodeinfo.NodeInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlin.runCatching

// todo well-knwonだけでパッケージ作ったほうがいいかも
class NodeinfoApi(private var httpClient: HttpClient) {

    init {
        httpClient = httpClient.config {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    suspend fun wellKnownNodeinfo(url: String): Result<NodeinfoList, MultiMError> {
        val get = try {
            httpClient.get("$url.well-known/nodeinfo")
        } catch (e: ServerResponseException) {
            return Err(
                MultiMHttpError(
                    HttpError.ServerError(e.response.status.value, e.message, e), e
                )
            )
        } catch (e: ClientRequestException) {
            return Err(
                MultiMHttpError(
                    HttpError.ClientError(e.response.status.value, e.message, e), e
                )
            )
        }
        return runCatching<NodeinfoList> { get.body() }.foldWithOk {
            MultiMJsonContentTransformError(
                it.localizedMessage
            )
        }
    }

    fun nodeinfoLink(nodeinfoList: NodeinfoList): Result<NodeinfoList.NodeinfoLink, MultiMError> {
        return runCatching<NodeinfoList.NodeinfoLink> {
            nodeinfoList.links.minByOrNull { it.rel.substringAfterLast("/", "0").toFloat() }!!
        }.foldWithOk {
            MultiMError(it.localizedMessage, it, ErrorType.API)
        }
    }


    // todo 強制で2.0のが返ってくるのでバージョンを識別する
    suspend fun nodeinfo(nodeinfoLink: NodeinfoList.NodeinfoLink): Result<NodeInfo, MultiMError> {

        val get = try {
            httpClient.get(nodeinfoLink.href)
        } catch (e: ServerResponseException) {
            return Err(
                MultiMHttpError(e)
            )
        } catch (e: ClientRequestException) {
            return Err(MultiMHttpError(e))
        }
        return runCatching<NodeInfo> { get.body() }.foldWithOk {
            MultiMJsonContentTransformError(
                it.localizedMessage
            )
        }
    }

    suspend fun nodeinfo(url: String): Result<NodeInfo, MultiMError> {
        return wellKnownNodeinfo(url)
            .flatMap { nodeinfoLink(it) }
            .flatMap { nodeinfo(it) }
    }
}
