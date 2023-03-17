package dev.usbharu.multim.misskey.v13

import dev.usbharu.multim.ServiceInfo
import dev.usbharu.multim.misskey.v13.api.MisskeyApis
import dev.usbharu.multim.misskey.v13.api.MisskeyMultiMApis
import dev.usbharu.multim.misskey.v13.common.api.MisskeyAccountApi
import dev.usbharu.multim.misskey.v13.common.api.MisskeyApiClient
import dev.usbharu.multim.misskey.v13.common.api.MisskeyEmojiApi
import dev.usbharu.multim.misskey.v13.common.api.MisskeyIApi
import dev.usbharu.multim.misskey.v13.common.api.MisskeyStatusApi
import dev.usbharu.multim.misskey.v13.common.api.MisskeyTimelineApi
import dev.usbharu.multim.model.SingleTokenAuth

object MisskeyV13Info : ServiceInfo(
    Regex("misskey"),
    Regex("^13\\.[\\d.]+"),
    { auth, baseUrl, client -> MisskeyApiClient(auth as SingleTokenAuth, baseUrl, client) },

    { apiClient -> MisskeyApis(apiClient as MisskeyApiClient) },
    {
        if (it is MisskeyApis) {
            MisskeyMultiMApis(
                MisskeyStatusApi(it),
                MisskeyAccountApi(it),
                MisskeyTimelineApi(it),
                MisskeyEmojiApi(it),
                MisskeyIApi(it)
            )
        } else {
            TODO()
        }
    }
)
