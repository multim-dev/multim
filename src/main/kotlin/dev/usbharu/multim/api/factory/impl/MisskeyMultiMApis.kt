package dev.usbharu.multim.api.factory.impl

import dev.usbharu.multim.api.common.impl.misskey.MisskeyAccountApi
import dev.usbharu.multim.api.common.impl.misskey.MisskeyStatusApi
import dev.usbharu.multim.api.factory.MultiMApis

class MisskeyMultiMApis(statusApi: MisskeyStatusApi, accountApi: MisskeyAccountApi) :
    MultiMApis(statusApi, accountApi)