package dev.usbharu.multim.misskey.v12

import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.api.MisskeyMultiMApis
import dev.usbharu.multim.misskey.v12.common.api.MisskeyAccountApi
import dev.usbharu.multim.misskey.v12.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v12.common.api.MisskeyStatusApi
import dev.usbharu.multim.misskey.v12.common.api.MisskeyTimelineApi

object MisskeyV12Info : ServiceInfo(
    Regex("misskey"),
    Regex("^12\\.[\\d.]+"),
    ::MisskeyApiClient,

    { apiClient -> MisskeyApis(apiClient as MisskeyApiClient) },
    {
        if (it is MisskeyApis) {
            MisskeyMultiMApis(MisskeyStatusApi(it),MisskeyAccountApi(it),MisskeyTimelineApi(it))
        }else {
            TODO()
        }
    }
)
