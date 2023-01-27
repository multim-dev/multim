package dev.usbharu.multim.factory

import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.api.PlatformApis
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
        for (service in services) {

            if (nodeInfo.getSoftwareName()
                    .matches(service.serviceNameMatchRegex) && nodeInfo.getSoftwareVersion()
                    .matches(service.versionMatchRegex)
            ) {
                val platFormApis = service.platFormApis(
                    service.apiClient(
                        token,
                        baseUrl,
                        httpClient
                    )
                )
                return service.multiMApis(
                    platFormApis
                ).apply { build(this, platFormApis) }
            }
        }
        TODO("サービスが見つからなかった際のしょり")
    }
}
