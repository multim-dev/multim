package dev.usbharu.multim

import dev.usbharu.multim.api.NodeinfoApi
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.PlatformApiFactory
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
     * @return 作成されたAPIクライアント
     */
    suspend fun createClient(url:String, factory: PlatformApiFactory):MultiMApis{
        val httpClient = HttpClient(CIO)
        val nodeinfo = NodeinfoApi(httpClient).nodeinfo(url)
        return factory.factory(nodeInfo = nodeinfo, httpClient, "", url)
    }
}