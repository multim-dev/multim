package dev.usbharu.multim

import dev.usbharu.multim.api.NodeinfoApi
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.PlatformApiFactory
import dev.usbharu.multim.multi.MultiAccountApiBase
import io.ktor.client.*
import io.ktor.client.engine.cio.*

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
    suspend fun createClient(url: String, token: String, factory: PlatformApiFactory,httpClient:HttpClient = HttpClient(CIO)): MultiMApis {

        val nodeinfo = NodeinfoApi(httpClient).nodeinfo(url)
        return factory.factory(nodeInfo = nodeinfo, httpClient, token, url)
    }

    fun createMultiAccountClients(serviceInfoList: List<ServiceInfo> = listOf()): MultiAccountApiBase {
        return MultiAccountApiBase(serviceInfoList)
    }
}
