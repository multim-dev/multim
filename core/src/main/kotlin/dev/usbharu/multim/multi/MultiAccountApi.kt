package dev.usbharu.multim.multi

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.factory.MultiMApis

class MultiAccountApi(statusApi: StatusApi, accountApi: AccountApi, timelineApi: TimelineApi) :
    MultiMApis(statusApi, accountApi, timelineApi) {
    constructor(apiBase: MultiAccountApiBase) : this(
        MultiAccountStatusApi(apiBase),
        MultiAccountAccountApi(apiBase),
        MultiAccountTimelineApi(apiBase)
    )
}
