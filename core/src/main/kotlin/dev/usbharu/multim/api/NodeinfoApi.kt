package dev.usbharu.multim.api

import dev.usbharu.multim.model.nodeinfo.v2_0.NodeInfo
import dev.usbharu.multim.model.nodeinfo.wellknown.NodeinfoList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

// todo well-knwonだけでパッケージ作ったほうがいいかも
class NodeinfoApi(val httpClient: HttpClient) {
    suspend fun wellKnownNodeinfo(url:String):NodeinfoList{
        return httpClient.get("$url/.well-known/nodeinfo").body()
    }

    fun nodeinfoLink(nodeinfoList: NodeinfoList):NodeinfoList.NodeinfoLink {
        return nodeinfoList.links.sortedBy { it.rel.substringAfterLast("/", "0").toFloat() }.first()
    }


    // todo 強制で2.0のが返ってくるのでバージョンを識別する
    suspend fun nodeinfo(nodeinfoLink: NodeinfoList.NodeinfoLink):NodeInfo{
        return httpClient.get(nodeinfoLink.href).body()
    }
}
