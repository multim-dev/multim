package dev.usbharu.multim.api.factory

import dev.usbharu.multim.api.common.AccountApi
import dev.usbharu.multim.api.common.StatusApi

abstract class MultiMApis(
    var statusApi: StatusApi,
    var accountApi: AccountApi
)