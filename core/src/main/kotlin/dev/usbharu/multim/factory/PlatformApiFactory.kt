package dev.usbharu.multim.factory

import dev.usbharu.multim.api.PlatformApis
import dev.usbharu.multim.model.Auth
import dev.usbharu.multim.model.wellknown.nodeinfo.NodeInfo
import io.ktor.client.*

interface PlatformApiFactory {
    @Deprecated("トークンが一つの場合にしか対応できないので廃止。")
    fun factory(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        token: String,
        baseUrl: String,
        build: MultiMApis.(PlatformApis) -> Unit = {}
    ): MultiMApis

    fun factory(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        auth: Auth,
        baseUrl: String,
        build:MultiMApis.(PlatformApis) -> Unit = {}
    ):MultiMApis
}
