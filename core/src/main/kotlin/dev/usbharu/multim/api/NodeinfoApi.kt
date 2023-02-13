package dev.usbharu.multim.api

import dev.usbharu.multim.model.wellknown.NodeinfoList
import dev.usbharu.multim.model.wellknown.nodeinfo.NodeInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

// todo well-knwonだけでパッケージ作ったほうがいいかも
class NodeinfoApi(private var httpClient: HttpClient) {

    init {
        httpClient = httpClient.config {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    suspend fun wellKnownNodeinfo(url: String): NodeinfoList {
        return httpClient.get("$url.well-known/nodeinfo").body()
    }

    fun nodeinfoLink(nodeinfoList: NodeinfoList): NodeinfoList.NodeinfoLink {
        return nodeinfoList.links.minByOrNull { it.rel.substringAfterLast("/", "0").toFloat() }!!
    }


    // todo 強制で2.0のが返ってくるのでバージョンを識別する
    suspend fun nodeinfo(nodeinfoLink: NodeinfoList.NodeinfoLink): NodeInfo {

        return httpClient.get(nodeinfoLink.href).body()
    }

    suspend fun nodeinfo(url: String): NodeInfo {
        return nodeinfo(nodeinfoLink(wellKnownNodeinfo(url)))
    }
}
