package dev.usbharu.multim.factory

import dev.usbharu.multim.Logger
import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.api.PlatformApis
import dev.usbharu.multim.model.Auth
import dev.usbharu.multim.model.SingleTokenAuth
import dev.usbharu.multim.model.wellknown.nodeinfo.NodeInfo
import io.ktor.client.*

class ServiceInfoFactory(private val services: List<ServiceInfo>) : PlatformApiFactory {
    override fun factory(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        token: String,
        baseUrl: String,
        build: MultiMApis.(PlatformApis) -> Unit
    ): MultiMApis {
        return this.factory(nodeInfo, httpClient, SingleTokenAuth(token), baseUrl, build)
    }

    override fun factory(
        nodeInfo: NodeInfo,
        httpClient: HttpClient,
        auth: Auth,
        baseUrl: String,
        build: MultiMApis.(PlatformApis) -> Unit
    ): MultiMApis {
        for (service in services) {

            if (nodeInfo.getSoftwareName()
                    .matches(service.serviceNameMatchRegex) && nodeInfo.getSoftwareVersion()
                    .matches(service.versionMatchRegex)
            ) {
                val platFormApis = service.platFormApis(
                    service.apiClient(
                        auth,
                        baseUrl,
                        httpClient
                    )
                )
                Logger.info("Platform Api Factory","Service Info factory found service.")
                return service.multiMApis(
                    platFormApis
                ).apply { build(this, platFormApis) }
            }
        }
        Logger.error("Platform Api Factory","Service Info factory cannot find service.")
        TODO("サービスが見つからなかった際のしょり")
    }
}
