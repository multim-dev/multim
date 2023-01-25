package dev.usbharu.multim.factory

import dev.usbharu.multim.api.common.PlatformApis
import dev.usbharu.multim.model.nodeinfo.NodeInfo
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
