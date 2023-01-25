package dev.usbharu.multim.factory

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.StatusApi

abstract class MultiMApis(
    var statusApi: StatusApi,
    var accountApi: AccountApi
)
