package dev.usbharu.multim.factory

import dev.usbharu.multim.api.PlatformApis
import dev.usbharu.multim.model.nodeinfo.v2_0.NodeInfo
import io.ktor.client.*

interface PlatformApiFactory {
    fun factory(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        token: String,
        baseUrl: String,
        build: MultiMApis.(PlatformApis) -> Unit = {}
    ): MultiMApis
}