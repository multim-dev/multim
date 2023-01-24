package dev.usbharu.multim.misskey.factory

import dev.usbharu.multim.factory.MultiMApis
import dev.usbharu.multim.misskey.v12.common.api.MisskeyAccountApi
import dev.usbharu.multim.misskey.v12.common.api.MisskeyStatusApi

class MisskeyMultiMApis(statusApi: MisskeyStatusApi, accountApi: MisskeyAccountApi) :
    MultiMApis(statusApi, accountApi)
