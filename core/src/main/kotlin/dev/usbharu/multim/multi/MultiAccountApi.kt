package dev.usbharu.multim.multi

import dev.usbharu.multim.api.AccountApi
import dev.usbharu.multim.api.EmojiApi
import dev.usbharu.multim.api.StatusApi
import dev.usbharu.multim.api.TimelineApi
import dev.usbharu.multim.factory.MultiMApis

class MultiAccountApi(statusApi: StatusApi, accountApi: AccountApi, timelineApi: TimelineApi,emojiApi: EmojiApi) :
    MultiMApis(statusApi, accountApi, timelineApi,emojiApi) {
    constructor(apiBase: MultiAccountApiBase) : this(
        MultiAccountStatusApi(apiBase),
        MultiAccountAccountApi(apiBase),
        MultiAccountTimelineApi(apiBase),
        MultiAccountEmojiApi(apiBase)
    )
}
