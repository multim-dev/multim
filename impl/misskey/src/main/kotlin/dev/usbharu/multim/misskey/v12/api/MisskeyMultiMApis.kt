package dev.usbharu.multim.misskey.v12.api

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.factory.MultiMApis

class MisskeyMultiMApis(statusApi: StatusApi, accountApi: AccountApi) :
    MultiMApis(statusApi, accountApi) {
}