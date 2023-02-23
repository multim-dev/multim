package dev.usbharu.multim.misskey.v12

import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.misskey.v12.api.MisskeyApis
import dev.usbharu.multim.misskey.v12.api.MisskeyMultiMApis
import dev.usbharu.multim.misskey.v12.common.api.*
import dev.usbharu.multim.model.SingleTokenAuth

object MisskeyV12Info : ServiceInfo(
    Regex("misskey"),
    Regex("^12\\.[\\d.]+"),
    { auth, baseUrl, client -> MisskeyApiClient(auth as SingleTokenAuth, baseUrl, client) },

    { apiClient -> MisskeyApis(apiClient as MisskeyApiClient) },
    {
        if (it is MisskeyApis) {
            MisskeyMultiMApis(MisskeyStatusApi(it),MisskeyAccountApi(it),MisskeyTimelineApi(it),MisskeyEmojiApi(it))
        }else {
            TODO()
        }
    }
)
