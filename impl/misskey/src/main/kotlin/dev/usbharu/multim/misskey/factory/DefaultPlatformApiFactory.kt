package dev.usbharu.multim.misskey.factory

import dev.usbharu.multim.api.common.PlatformApis
import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.factory.PlatformApiFactory
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.common.api.MisskeyAccountApi
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.common.api.MisskeyStatusApi
import dev.usbharu.multim.model.nodeinfo.NodeInfo
import io.ktor.client.*


//todo factoryはbuilderを返すだけにする。
// ↑認証方式が違うAPIに対応できないため
open class DefaultPlatformApiFactory : PlatformApiFactory {
    override fun factory(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        token: String,
        baseUrl: String,
        build: MultiMApis.(PlatformApis) -> Unit
    ): MultiMApis {
        if (nodeInfo.software.name == "misskey") {
            return misskey(nodeInfo, httpClient, token, baseUrl, build)
        }
        TODO()
    }

    protected fun misskey(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        token: String,
        baseUrl: String,
        build: (MultiMApis.(PlatformApis) -> Unit)?
    ): MisskeyMultiMApis {
        if (nodeInfo.software.version.startsWith("12.")) {
            val misskeyApis = MisskeyApis(MisskeyApiClient(token, baseUrl, httpClient))
            val misskeyMultiMApis =
                MisskeyMultiMApis(MisskeyStatusApi(misskeyApis), MisskeyAccountApi(misskeyApis))
            return misskeyMultiMApis.apply { build?.invoke(this, misskeyApis) }
        }
        TODO()
    }
}
