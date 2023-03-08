package dev.usbharu.multim.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.flatMap
import dev.usbharu.multim.Logger
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

// todo well-knwonだけでパッケージ作ったほうがいいかも
class NodeinfoApi(private var httpClient: HttpClient) {

    init {
        httpClient = httpClient.config {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }

    suspend fun wellKnownNodeinfo(url: String): MultiMResult<NodeinfoList> {
        Logger.info("Nodeinfo Api", "START Get $url.well-known/nodeinfo")
        val get = try {
            httpClient.get("$url.well-known/nodeinfo")
        } catch (e: ServerResponseException) {
            Logger.warn(
                "Nodeinfo Api",
                "FAILURE Server Response Exception Get $url.well-known/nodeinfo"
            )
            return Err(
                MultiMHttpError(
                    HttpError.ServerError(e.response.status.value, e.message, e), e
                )
            )
        } catch (e: ClientRequestException) {
            Logger.warn(
                "Nodeinfo Api",
                "FAILURE Client Request Exception Get $url.well-known/nodeinfo"
            )
            return Err(
                MultiMHttpError(
                    HttpError.ClientError(e.response.status.value, e.message, e), e
                )
            )
        }
        return runCatching<NodeinfoList> { get.body() }.foldWithOk({
            Logger.info("Nodeinfo Api", "SUCCESS Get $url.well-known/nodeinfo")
            Ok(it)
        }) {
            Logger.warn("Nodeinfo Api", "FAILURE Get body")
            MultiMJsonContentTransformError(
                it.localizedMessage
            )
        }
    }

    fun nodeinfoLink(nodeinfoList: NodeinfoList): MultiMResult<NodeinfoList.NodeinfoLink> {
        Logger.info("Nodeinfo Api", "START Get nodeinfo link")
        return runCatching<NodeinfoList.NodeinfoLink> {
            nodeinfoList.links.minByOrNull { it.rel.substringAfterLast("/", "0").toFloat() }!!
        }.foldWithOk({
            Logger.info("Nodeinfo Api", "SUCCESS Get nodeinfo link")
            Ok(it)
        }) {
            Logger.warn("Nodeinfo Api", "FAILURE Get nodeinfo link")
            MultiMError(it.localizedMessage, it, ErrorType.API)
        }
    }


    // todo 強制で2.0のが返ってくるのでバージョンを識別する
    suspend fun nodeinfo(nodeinfoLink: NodeinfoList.NodeinfoLink): MultiMResult<NodeInfo> {

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

    suspend fun nodeinfo(url: String): MultiMResult<NodeInfo> {
        return wellKnownNodeinfo(url)
            .flatMap { nodeinfoLink(it) }
            .flatMap { nodeinfo(it) }
    }
}
